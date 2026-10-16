package com.sample.tmdb.credit

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent

sealed interface PeopleUIEvent : UiEvent {
    data class ShowWarning(override val message: String) :
        PeopleUIEvent,
        UiEvent.Warning
    data class Navigate(override val route: TMDbNavKey) :
        PeopleUIEvent,
        UiEvent.Navigation
}
