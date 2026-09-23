package com.sample.tmdb.bookmark

import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.common.utils.UiEvent

sealed interface BookmarkUiEvent : UiEvent {
    data class ShowWarning(override val message: String) :
        BookmarkUiEvent,
        UiEvent.Warning
    data class Navigate(override val route: TMDbNavKey) :
        BookmarkUiEvent,
        UiEvent.Navigation
}
