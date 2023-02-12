package com.sample.tmdb.ui.feed

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.model.TMDbType
import com.sample.tmdb.ui.Content
import com.sample.tmdb.ui.common.DestinationBar

@Composable
fun MovieFeedScreen(
    onClick: (TMDbItem) -> Unit,
    navController: NavController,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    FeedScreen(
        viewModel = viewModel,
        type = TMDbType.MOVIES,
        navController = navController,
        onClick = onClick,
        R.string.movies
    )
}

@Composable
fun TVShowFeedScreen(
    onClick: (TMDbItem) -> Unit,
    navController: NavController,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    FeedScreen(
        viewModel = viewModel,
        type = TMDbType.TV_SERIES,
        navController = navController,
        onClick = onClick,
        R.string.tv_series
    )
}

@Composable
private fun <T : TMDbItem> FeedScreen(
    viewModel: BaseFeedViewModel<T>,
    type: TMDbType,
    navController: NavController,
    onClick: (TMDbItem) -> Unit,
    @StringRes resourceId: Int

) {
    Content(viewModel = viewModel) { feeds ->
        Box {
            FeedCollectionList(type, navController, feeds, onClick)
            DestinationBar(
                title = stringResource(
                    R.string.app_title, stringResource(resourceId)
                ), navController = navController, type = type
            )
        }
    }
}