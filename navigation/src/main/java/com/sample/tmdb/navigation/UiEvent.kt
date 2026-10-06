package com.sample.tmdb.navigation

interface UiEvent {
    interface Warning : UiEvent {
        val message: String
    }
    interface Navigation : UiEvent {
        val route: TMDbNavKey
    }
    interface NavigateUp : UiEvent
}
