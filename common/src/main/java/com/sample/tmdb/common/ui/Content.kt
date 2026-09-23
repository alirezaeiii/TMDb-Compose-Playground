package com.sample.tmdb.common.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.UiEvent
import com.sample.tmdb.common.utils.ViewState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T, S, E : UiEvent> Content(
    viewModel: BaseViewModel<T, S, E>,
    languageViewModel: LanguageViewModel? = null,
    scaffoldState: ScaffoldState? = null,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    successScreen: @Composable (ViewState<T>, T) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        languageViewModel?.languageCode?.collectLatest { newLanguageCode ->
            viewModel.refreshOnLanguageChange(newLanguageCode)
        }
    }

    when {
        state.items != null -> {
            successScreen(state, state.items)
        }

        state.isLoading -> {
            TMDbProgressBar()
        }

        state.error.isNotEmpty() && !state.isWarning -> {
            ErrorScreen(
                message = state.error,
                modifier = Modifier.fillMaxSize(),
                refresh = viewModel::refresh,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Warning -> scaffoldState?.snackbarHostState?.showSnackbar(event.message)
                is UiEvent.Navigation -> onNavigate(event.route)
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }
}
