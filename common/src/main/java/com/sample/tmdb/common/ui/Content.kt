package com.sample.tmdb.common.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.Resource

@Composable
fun <T> Content(
    viewModel: BaseViewModel<T>,
    successScreen: @Composable (T) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> TMDbProgressBar()
        is Resource.Success -> successScreen(resource.data)
        is Resource.Error -> ErrorScreen(
            message = resource.message, Modifier.fillMaxSize(),
            refresh = viewModel::refresh
        )
    }
}