package com.sample.tmdb.detail

import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.common.utils.UiEvent

sealed interface DetailUiEvent : UiEvent {
    data class ShowWarning(override val message: String) :
        DetailUiEvent,
        UiEvent.Warning
    data class Navigate(override val route: TMDbNavKey) :
        DetailUiEvent,
        UiEvent.Navigation
    data object NavigateUp : DetailUiEvent, UiEvent.NavigateUp
}
