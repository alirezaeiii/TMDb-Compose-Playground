package com.sample.tmdb.common.ui

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent as NavUiEvent

sealed interface UiEvent : NavUiEvent {
    data class ShowWarning(override val message: String) :
        UiEvent,
        NavUiEvent.Warning
    data class Navigate(override val route: TMDbNavKey) :
        UiEvent,
        NavUiEvent.Navigation
    data object NavigateUp : UiEvent, NavUiEvent.NavigateUp
}
