package com.android.sample.tmdb.ui.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.ui.Content

@Composable
fun MovieDetailScreen(
    upPress: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        DetailScreen(detailWrapper = it, upPress = upPress)
    }
}

@Composable
fun TVShowDetailScreen(
    upPress: () -> Unit,
    viewModel: TVShowDetailViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        DetailScreen(detailWrapper = it, upPress = upPress)
    }
}