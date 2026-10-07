package com.sample.tmdb.paging.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.PagingScreen
import com.sample.tmdb.paging.R

@Composable
fun MoviePagingScreen(
    viewModel: MoviePagingViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
    sortType: SortType,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            when (sortType) {
                SortType.TRENDING -> R.string.trending
                SortType.DISCOVER -> R.string.discover
                SortType.UPCOMING -> R.string.upcoming
                SortType.NOW_PLAYING -> R.string.now_playing
                SortType.MOST_POPULAR -> R.string.popular
                SortType.HIGHEST_RATED -> R.string.highest_rate
                SortType.SIMILAR -> R.string.similar_items
            },
            stringResource(commonR.string.movies),
        ),
    )
}

@Composable
fun TVShowPagingScreen(
    viewModel: TvShowPagingViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
    sortType: SortType,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            when (sortType) {
                SortType.TRENDING -> R.string.trending
                SortType.DISCOVER -> R.string.discover
                SortType.UPCOMING -> R.string.on_the_air
                SortType.NOW_PLAYING -> R.string.airing_today
                SortType.MOST_POPULAR -> R.string.popular
                SortType.HIGHEST_RATED -> R.string.highest_rate
                SortType.SIMILAR -> R.string.similar_items
            },
            stringResource(commonR.string.tv_series),
        ),
    )
}

@Composable
private fun <T : TMDbItem> PagingScreen(
    viewModel: BaseMainPagingViewModel<T>,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
    title: String,
) {
    Box {
        PagingScreen(
            viewModel = viewModel,
            onNavigate = onNavigate,
            onNavigateUp = onNavigateUp,
        )
        DestinationBar(
            title = title,
            upPress = viewModel::onNavigateUp,
            onSearchClicked = viewModel::onSearchClick,
        )
    }
}
