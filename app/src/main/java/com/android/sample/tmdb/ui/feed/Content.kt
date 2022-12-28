package com.android.sample.tmdb.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.sample.tmdb.domain.model.FeedWrapper
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.common.ErrorScreen
import com.android.sample.tmdb.ui.common.TMDbProgressBar
import com.android.sample.tmdb.utils.Resource

@Composable
fun <T : TMDbItem> Content(
    viewModel: BaseFeedViewModel<T>,
    SuccessScreen: @Composable (t: List<FeedWrapper<T>>) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> TMDbProgressBar()
        is Resource.Success -> SuccessScreen(resource.data)
        is Resource.Error -> ErrorScreen(message = resource.message, refresh = viewModel::refresh)
    }
}

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