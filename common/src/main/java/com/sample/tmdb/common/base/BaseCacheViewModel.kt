package com.sample.tmdb.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.common.ui.ViewState
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.domain.utils.Async
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseCacheViewModel<DataType, QueryType, FetchType>(
    private val repository: BaseCacheRepository<DataType, QueryType, FetchType>,
    private val queryParam: QueryType? = null,
    private val fetchParam: FetchType? = null,
    languageRepository: LanguageRepository? = null,
    loadDataOnInit: Boolean = true,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewState<DataType>(isLoading = true))
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var job: Job? = null

    private var lastLanguage: String? = null

    private var hasCriticalError = false

    init {
        if (loadDataOnInit) {
            refresh()
        }
        lastLanguage = languageRepository?.languageCode?.value
    }

    fun refresh(isUserRefresh: Boolean = true, languageCode: String? = null) {
        execute(languageCode) {
            repository.getResult(queryParam, fetchParam, isUserRefresh)
        }
    }

    fun refreshOnLanguageChange(language: String) {
        if (language != lastLanguage && !hasCriticalError) {
            refresh(true, language)
        }
    }

    private fun execute(languageCode: String? = null, block: () -> Flow<Async<DataType>>) {
        job?.cancel()
        job = block.invoke().onEach { repoResource ->
            reduce(repoResource, languageCode)
        }.launchIn(viewModelScope)
    }

    private suspend fun reduce(resource: Async<DataType>, languageCode: String? = null) {
        when (resource) {
            is Async.Loading -> {
                updateState {
                    it.copy(
                        isLoading = !resource.isRefreshing,
                        isRefreshing = resource.isRefreshing,
                        error = "",
                    )
                }
            }

            is Async.Success -> {
                updateState {
                    ViewState(items = resource.data)
                }
                languageCode?.let {
                    lastLanguage = it
                }
                hasCriticalError = false
            }

            is Async.Error -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        error = resource.message,
                        isWarning = resource.isWarning,
                    )
                }
                if (resource.isWarning) {
                    emitWarning(resource.message)
                } else {
                    hasCriticalError = true
                }
            }
        }
    }

    private fun updateState(reducer: (ViewState<DataType>) -> ViewState<DataType>) {
        _state.update(reducer)
    }

    private suspend fun emitWarning(message: String) {
        _uiEvent.emit(UiEvent.ShowWarning(message))
    }

    protected fun emitEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}
