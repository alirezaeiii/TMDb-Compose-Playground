package com.sample.tmdb.person.paging

import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent

sealed interface PagingPersonUiEvent : UiEvent {
    data class Navigate(override val route: TMDbNavKey) :
        PagingPersonUiEvent,
        UiEvent.Navigation

    data object NavigateUp : PagingPersonUiEvent, UiEvent.NavigateUp
}
