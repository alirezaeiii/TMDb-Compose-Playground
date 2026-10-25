package com.sample.tmdb.preson

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent

sealed interface PersonUiEvent : UiEvent {
    data class Navigate(override val route: TMDbNavKey) :
        PersonUiEvent,
        UiEvent.Navigation
    data object NavigateUp : PersonUiEvent, UiEvent.NavigateUp
}
