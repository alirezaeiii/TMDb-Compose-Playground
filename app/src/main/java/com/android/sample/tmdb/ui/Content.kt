package com.android.sample.tmdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.android.sample.tmdb.ui.common.ErrorScreen
import com.android.sample.tmdb.ui.common.TMDbProgressBar
import com.android.sample.tmdb.utils.Resource

@Composable
fun <T> Content(
    viewModel: BaseViewModel<T>,
    SuccessScreen: @Composable (t: T) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> TMDbProgressBar()
        is Resource.Success -> SuccessScreen(resource.data)
        is Resource.Error -> ErrorScreen(message = resource.message, refresh = viewModel::refresh)
    }
}