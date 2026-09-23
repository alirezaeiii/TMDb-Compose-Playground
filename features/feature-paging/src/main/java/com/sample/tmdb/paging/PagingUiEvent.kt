package com.sample.tmdb.paging

import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.common.utils.UiEvent

sealed interface PagingUiEvent : UiEvent {
    data class Navigate(override val route: TMDbNavKey) :
        PagingUiEvent,
        UiEvent.Navigation
    data object NavigateUp : PagingUiEvent, UiEvent.NavigateUp
}
