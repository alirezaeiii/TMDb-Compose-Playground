package com.sample.tmdb.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
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
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.model.TMDbType
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.LoadingRow
import com.sample.tmdb.common.ui.component.TMDbItemContent
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.toDp
import com.sample.tmdb.paging.main.movie.DiscoverMoviesViewModel
import com.sample.tmdb.paging.main.movie.NowPlayingMoviesViewModel
import com.sample.tmdb.paging.main.movie.PopularMoviesViewModel
import com.sample.tmdb.paging.main.movie.TopRatedMoviesViewModel
import com.sample.tmdb.paging.main.movie.TrendingMoviesViewModel
import com.sample.tmdb.paging.main.movie.UpcomingMoviesViewModel
import com.sample.tmdb.paging.main.tvshow.AiringTodayTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.DiscoverTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.OnTheAirTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.PopularTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.TopRatedTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.TrendingTvSeriesViewModel
import com.sample.tmdb.common.R as R1

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
            stringResource(R1.string.movies)
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
            stringResource(R1.string.movies)
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
            stringResource(R1.string.movies)
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
            stringResource(R1.string.movies)
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
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun DiscoverMovieScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: DiscoverMoviesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.MOVIES, title = stringResource(
            R.string.discover,
            stringResource(R1.string.movies)
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
            stringResource(R1.string.tv_series)
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
            stringResource(R1.string.tv_series)
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
            stringResource(R1.string.tv_series)
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
            stringResource(R1.string.tv_series)
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
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun DiscoverTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    viewModel: DiscoverTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(
        viewModel = viewModel, onClick = onClick, upPress = upPress, navController = navController,
        type = TMDbType.TV_SERIES, title = stringResource(
            R.string.discover,
            stringResource(R1.string.tv_series)
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