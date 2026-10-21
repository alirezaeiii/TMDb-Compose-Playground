package com.sample.tmdb.detail

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent

sealed interface DetailUiEvent : UiEvent {
    data class Navigate(override val route: TMDbNavKey) :
        DetailUiEvent,
        UiEvent.Navigation
    data object NavigateUp : DetailUiEvent, UiEvent.NavigateUp
}
