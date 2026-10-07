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
        title = pagingTitle(
            sortType,
            commonR.string.movies,
            R.string.upcoming,
            R.string.now_playing,
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
        title = pagingTitle(
            sortType,
            commonR.string.tv_series,
            R.string.on_the_air,
            R.string.airing_today,
        ),
    )
}

@Composable
private fun pagingTitle(sortType: SortType, itemType: Int, upcomingRes: Int, nowPlayingRes: Int): String {
    val titleRes = when (sortType) {
        SortType.TRENDING -> R.string.trending
        SortType.DISCOVER -> R.string.discover
        SortType.UPCOMING -> upcomingRes
        SortType.NOW_PLAYING -> nowPlayingRes
        SortType.MOST_POPULAR -> R.string.popular
        SortType.HIGHEST_RATED -> R.string.highest_rate
        SortType.SIMILAR -> R.string.similar_items
    }
    return stringResource(titleRes, stringResource(itemType))
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
