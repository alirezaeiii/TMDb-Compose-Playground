package com.sample.tmdb.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.common.utils.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<T, S>(
    private val repository: BaseRepository<T, S>,
    languageRepository: LanguageRepository? = null,
    private val id: S? = null,
    shouldRefreshInInit: Boolean = true,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewState<T>(isLoading = true))
    val state = _state.asStateFlow()

    private val _showWarningUiEvent = MutableSharedFlow<UiEvent>()
    val showWarningUiEvent = _showWarningUiEvent.asSharedFlow()

    sealed class UiEvent {
        data class ShowWarning(val message: String) : UiEvent()
    }

    init {
        if (shouldRefreshInInit) {
            refresh()
        }
    }

    private var lastLanguage: String? = null

    private var hasCriticalError = false

    init {
        lastLanguage = languageRepository?.languageCode?.value
    }

    fun refresh(isUserRefresh: Boolean = false, languageCode: String? = null) {
        execute(languageCode) {
            repository.getResult(isUserRefresh, id)
        }
    }

    fun refreshOnLanguageChange(language: String) {
        if (language != lastLanguage && !hasCriticalError) {
            refresh(true, language)
        }
    }

    private fun execute(languageCode: String? = null, block: () -> Flow<Async<T>>) {
        block.invoke().onEach { repoResource -> reduce(repoResource, languageCode) }
            .launchIn(viewModelScope)
    }

    private suspend fun reduce(resource: Async<T>, languageCode: String? = null) {
        when (resource) {
            is Async.Loading -> {
                _state.update {
                    it.copy(
                        isLoading = !resource.isRefreshing,
                        isRefreshing = resource.isRefreshing,
                        error = "",
                    )
                }
            }

            is Async.Success -> {
                _state.value = ViewState(items = resource.data)
                languageCode?.let {
                    lastLanguage = it
                }
                hasCriticalError = false
            }

            is Async.Error -> {
                _state.update {
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

    private suspend fun emitWarning(message: String) {
        _showWarningUiEvent.emit(UiEvent.ShowWarning(message))
    }
}
