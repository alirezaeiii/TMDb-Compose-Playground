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
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T, S> Content(
    viewModel: BaseViewModel<T, S>,
    languageViewModel: LanguageViewModel? = null,
    scaffoldState: ScaffoldState? = null,
    successScreen: @Composable (T) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        languageViewModel?.languageCode?.collectLatest { newLanguageCode ->
            viewModel.refreshOnLanguageChange(newLanguageCode)
        }
    }

    when {
        state.items != null -> {
            successScreen(state.items)
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
        viewModel.showWarningUiEvent.collectLatest { event ->
            when (event) {
                is BaseViewModel.UiEvent.ShowWarning ->
                    scaffoldState?.snackbarHostState?.showSnackbar(event.message)
            }
        }
    }
}
