package com.sample.tmdb.common.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.tmdb.common.base.BaseCacheViewModel
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.navigation.TMDbNavKey
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <DataType, QueryType, FetchType> CacheContent(
    viewModel: BaseCacheViewModel<DataType, QueryType, FetchType>,
    languageViewModel: LanguageViewModel? = null,
    scaffoldState: ScaffoldState? = null,
    refresh: () -> Unit = { viewModel.refresh(true) },
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    successScreen: @Composable (ViewState<DataType>) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(languageViewModel, viewModel) {
        languageViewModel?.languageCode?.collectLatest { newLanguageCode ->
            viewModel.refreshOnLanguageChange(newLanguageCode)
        }
    }

    when {
        state.isLoading -> TMDbProgressBar()

        state.error.isNotEmpty() && !state.isWarning ->
            ErrorScreen(state.error) { refresh.invoke() }

        else -> successScreen(state)
    }
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowWarning -> scaffoldState?.snackbarHostState?.showSnackbar(event.message)
                is UiEvent.Navigate -> onNavigate(event.route)
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }
}
