package com.sample.tmdb.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.common.ui.ViewState
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.domain.utils.Async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseCoreViewModel<T>(languageRepository: LanguageRepository? = null) : ViewModel() {

    private val _state = MutableStateFlow(ViewState<T>(isLoading = true))
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var lastLanguage: String? = languageRepository?.languageCode?.value

    private var hasCriticalError = false

    protected abstract fun executeRefresh(isUserRefresh: Boolean, languageCode: String?)

    fun refreshOnLanguageChange(language: String) {
        if (language != lastLanguage && !hasCriticalError) {
            executeRefresh(true, language)
        }
    }

    protected suspend fun reduce(resource: Async<T>, languageCode: String? = null) {
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

    protected fun updateState(reducer: (ViewState<T>) -> ViewState<T>) {
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
