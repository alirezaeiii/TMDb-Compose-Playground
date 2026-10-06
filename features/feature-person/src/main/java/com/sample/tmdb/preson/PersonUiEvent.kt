package com.sample.tmdb.preson

import com.sample.tmdb.navigation.UiEvent

sealed interface PersonUiEvent : UiEvent {
    data class ShowWarning(override val message: String) :
        PersonUiEvent,
        UiEvent.Warning
    data object NavigateUp : PersonUiEvent, UiEvent.NavigateUp
}
