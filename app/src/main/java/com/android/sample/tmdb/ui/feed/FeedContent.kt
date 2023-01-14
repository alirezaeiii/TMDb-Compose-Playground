package com.android.sample.tmdb.ui.feed

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.FeedWrapper
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.Content
import com.android.sample.tmdb.ui.common.DestinationBar

@Composable
fun MovieFeedScreen(
    onClick: (TMDbItem) -> Unit,
    navController: NavController,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        Box {
            FeedScreen(
                type = TMDbType.MOVIES,
                navController = navController,
                onClick = onClick,
                feeds = it,
                R.string.movies
            )
        }
    }
}

@Composable
fun TVShowFeedScreen(
    onClick: (TMDbItem) -> Unit,
    navController: NavController,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        FeedScreen(
            type = TMDbType.TV_SERIES,
            navController = navController,
            onClick = onClick,
            feeds = it,
            R.string.tv_series
        )
    }
}

@Composable
fun <T : TMDbItem> FeedScreen(
    type: TMDbType,
    navController: NavController,
    onClick: (TMDbItem) -> Unit,
    feeds: List<FeedWrapper<T>>,
    @StringRes resourceId: Int

) {
    Box {
        FeedCollectionList(type, navController, feeds, onClick)
        DestinationBar(
            title = stringResource(
                R.string.app_title, stringResource(resourceId)
            )
        )
    }
}