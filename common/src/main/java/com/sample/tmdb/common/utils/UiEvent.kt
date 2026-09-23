package com.sample.tmdb.common.utils

import com.sample.tmdb.common.ui.TMDbNavKey

interface UiEvent {
    interface Warning : UiEvent {
        val message: String
    }
    interface Navigation : UiEvent {
        val route: TMDbNavKey
    }
    interface NavigateUp : UiEvent
}
