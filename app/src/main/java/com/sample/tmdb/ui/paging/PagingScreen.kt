package com.sample.tmdb.ui.paging

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.model.TMDbType
import com.sample.tmdb.ui.common.*
import com.sample.tmdb.ui.paging.main.movie.*
import com.sample.tmdb.ui.paging.main.tvshow.*
import com.sample.tmdb.utils.toDp

@Composable
fun TrendingMovieScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: TrendingMoviesViewModel = hiltViewModel(),
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.MOVIES, title = stringResource(
            R.string.trending,
            stringResource(R.string.movies)
        )
    )
}

@Composable
fun PopularMovieScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.MOVIES, title = stringResource(
            R.string.popular,
            stringResource(R.string.movies)
        )
    )
}

@Composable
fun NowPlayingMovieScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: NowPlayingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.MOVIES, title = stringResource(
            R.string.now_playing,
            stringResource(R.string.movies)
        )
    )
}

@Composable
fun UpcomingMovieScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.MOVIES, title = stringResource(
            R.string.upcoming,
            stringResource(R.string.movies)
        )
    )
}

@Composable
fun TopRatedMovieScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.MOVIES, title = stringResource(
            R.string.highest_rate,
            stringResource(R.string.movies)
        )
    )
}

@Composable
fun TrendingTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: TrendingTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.TV_SERIES, title = stringResource(
            R.string.trending,
            stringResource(R.string.tv_series)
        )
    )
}

@Composable
fun PopularTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: PopularTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.TV_SERIES, title = stringResource(
            R.string.popular,
            stringResource(R.string.tv_series)
        )
    )
}

@Composable
fun AiringTodayTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.TV_SERIES, title = stringResource(
            R.string.airing_today,
            stringResource(R.string.tv_series)
        )
    )
}

@Composable
fun OnTheAirTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.TV_SERIES, title = stringResource(
            R.string.on_the_air,
            stringResource(R.string.tv_series)
        )
    )
}

@Composable
fun TopRatedTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.TV_SERIES, title = stringResource(
            R.string.highest_rate,
            stringResource(R.string.tv_series)
        )
    )
}

@Composable
private fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    type: TMDbType,
    title: String,
) {
    Box {
        PagingScreen(viewModel, onClick)
        DestinationBar(title = title, upPress = upPress, navController = navController, type = type)
    }
}

@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
) {
    val lazyTMDbItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    when (lazyTMDbItems.loadState.refresh) {
        is LoadState.Loading -> {
            TMDbProgressBar()
        }
        is LoadState.Error -> {
            val message =
                (lazyTMDbItems.loadState.refresh as? LoadState.Error)?.error?.message ?: return

            ErrorScreen(
                message = message,
                modifier = Modifier.fillMaxSize(),
                refresh = { lazyTMDbItems.retry() }
            )
        }
        else -> {
            LazyTMDbItemGrid(lazyTMDbItems, onClick)
        }
    }
}


@Composable
private fun <T : TMDbItem> LazyTMDbItemGrid(
    lazyTMDbItems: LazyPagingItems<T>,
    onClick: (TMDbItem) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(
            start = Dimens.PaddingMedium,
            end = Dimens.PaddingMedium,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(
                    Dimens.PaddingMedium
                )
        ),
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.PaddingMedium,
            Alignment.CenterHorizontally
        ),
        content = {

            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
            }

            items(lazyTMDbItems.itemCount) { index ->
                val tmdbItem = lazyTMDbItems[index]
                tmdbItem?.let {
                    TMDbItemContent(
                        it,
                        Modifier
                            .height(320.dp)
                            .padding(vertical = Dimens.PaddingMedium),
                        onClick
                    )
                }
            }

            when (lazyTMDbItems.loadState.append) {
                is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingRow(modifier = Modifier.padding(vertical = Dimens.PaddingMedium))
                    }
                }
                is LoadState.Error -> {
                    val message =
                        (lazyTMDbItems.loadState.append as? LoadState.Error)?.error?.message
                            ?: return@LazyVerticalGrid

                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(
                            message = message,
                            modifier = Modifier.padding(vertical = Dimens.PaddingMedium),
                            refresh = { lazyTMDbItems.retry() })
                    }
                }
                else -> {}
            }
        })
}