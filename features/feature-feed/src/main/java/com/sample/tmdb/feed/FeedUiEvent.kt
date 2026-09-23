package com.sample.tmdb.feed

import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.common.utils.UiEvent

sealed interface FeedUiEvent : UiEvent {
    data class ShowWarning(override val message: String) :
        FeedUiEvent,
        UiEvent.Warning
    data class Navigate(override val route: TMDbNavKey) :
        FeedUiEvent,
        UiEvent.Navigation
}
