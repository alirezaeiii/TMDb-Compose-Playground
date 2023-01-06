package com.android.sample.tmdb.ui.feed

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.Content

@Composable
fun MovieFeedScreen(
    onClick: (TMDbItem) -> Unit,
    navController: NavController,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        FeedCollectionList(TMDbType.MOVIES, navController, it, onClick)
    }
}

@Composable
fun TVShowFeedScreen(
    onClick: (TMDbItem) -> Unit,
    navController: NavController,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        FeedCollectionList(TMDbType.TV_SERIES, navController, it, onClick)
    }
}