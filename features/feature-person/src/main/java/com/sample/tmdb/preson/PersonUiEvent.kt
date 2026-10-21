package com.sample.tmdb.preson

import com.sample.tmdb.navigation.UiEvent

sealed interface PersonUiEvent : UiEvent {
    data object NavigateUp : PersonUiEvent, UiEvent.NavigateUp
}
