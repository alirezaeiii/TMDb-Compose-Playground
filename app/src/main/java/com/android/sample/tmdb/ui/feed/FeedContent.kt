package com.android.sample.tmdb.ui.feed

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.Content

@Composable
fun FeedMovieScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        FeedCollectionList(it, onClick)
    }
}

@Composable
fun FeedTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        FeedCollectionList(it, onClick)
    }
}