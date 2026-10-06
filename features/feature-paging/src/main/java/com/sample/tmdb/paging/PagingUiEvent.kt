package com.sample.tmdb.paging

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent

sealed interface PagingUiEvent : UiEvent {
    data class Navigate(override val route: TMDbNavKey) :
        PagingUiEvent,
        UiEvent.Navigation
    data object NavigateUp : PagingUiEvent, UiEvent.NavigateUp
}
