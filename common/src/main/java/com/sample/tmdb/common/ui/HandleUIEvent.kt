package com.sample.tmdb.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HandleUiEvents(uiEvent: Flow<UiEvent>, onNavigate: (TMDbNavKey) -> Unit, onNavigateUp: () -> Unit) {
    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigation -> onNavigate(event.route)
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }
}
