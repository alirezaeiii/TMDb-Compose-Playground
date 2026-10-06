package com.sample.tmdb.bookmark

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent

sealed interface BookmarkUiEvent : UiEvent {
    data class ShowWarning(override val message: String) :
        BookmarkUiEvent,
        UiEvent.Warning
    data class Navigate(override val route: TMDbNavKey) :
        BookmarkUiEvent,
        UiEvent.Navigation
}
