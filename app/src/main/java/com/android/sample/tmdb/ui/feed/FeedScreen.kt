package com.android.sample.tmdb.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun FeedMovieScreen(
    navController: NavHostController,
    bottomPadding: Dp,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    Content(viewModel) {
        FeedCollectionList(it, bottomPadding) {}
    }
}

@Composable
fun FeedTVShowScreen(
    navController: NavHostController,
    bottomPadding: Dp,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    Content(viewModel) {
        FeedCollectionList(it, bottomPadding) {}
    }
}