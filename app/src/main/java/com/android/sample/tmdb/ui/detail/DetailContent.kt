package com.android.sample.tmdb.ui.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.domain.model.TMDbItemDetails
import com.android.sample.tmdb.ui.Content

@Composable
fun MovieDetailScreen(
    upPress: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    DetailScreen(viewModel = viewModel, upPress = upPress)
}

@Composable
fun TVShowDetailScreen(
    upPress: () -> Unit,
    viewModel: TVShowDetailViewModel = hiltViewModel()
) {
    DetailScreen(viewModel = viewModel, upPress = upPress)
}

@Composable
fun <T : TMDbItemDetails> DetailScreen(
    viewModel: BaseDetailViewModel<T>,
    upPress: () -> Unit
) {
    Content(viewModel = viewModel) {
        DetailScreen(detailWrapper = it, upPress = upPress)
    }
}