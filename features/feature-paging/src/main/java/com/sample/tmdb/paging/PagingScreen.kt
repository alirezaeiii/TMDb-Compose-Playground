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
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.LoadingRow
import com.sample.tmdb.common.ui.component.TMDbItemContent
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.toDp
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.paging.main.movie.DiscoverMoviesViewModel
import com.sample.tmdb.paging.main.movie.NowPlayingMoviesViewModel
import com.sample.tmdb.paging.main.movie.PopularMoviesViewModel
import com.sample.tmdb.paging.main.movie.SimilarMoviesViewModel
import com.sample.tmdb.paging.main.movie.TopRatedMoviesViewModel
import com.sample.tmdb.paging.main.movie.TrendingMoviesViewModel
import com.sample.tmdb.paging.main.movie.UpcomingMoviesViewModel
import com.sample.tmdb.paging.main.tvshow.AiringTodayTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.DiscoverTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.OnTheAirTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.PopularTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.SimilarTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.TopRatedTvSeriesViewModel
import com.sample.tmdb.paging.main.tvshow.TrendingTvSeriesViewModel
import com.sample.tmdb.common.R as R1

@Composable
fun TrendingMovieScreen(
    navController: NavController,
    viewModel: TrendingMoviesViewModel = hiltViewModel(),
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.trending,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun PopularMovieScreen(
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.popular,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun NowPlayingMovieScreen(
    navController: NavController,
    viewModel: NowPlayingMoviesViewModel = hiltViewModel()
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.now_playing,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun UpcomingMovieScreen(
    navController: NavController,
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.upcoming,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun TopRatedMovieScreen(
    navController: NavController,
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.highest_rate,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun DiscoverMovieScreen(
    navController: NavController,
    viewModel: DiscoverMoviesViewModel = hiltViewModel()
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.discover,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun SimilarMovieScreen(
    navController: NavController,
    viewModel: SimilarMoviesViewModel = hiltViewModel()
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.similar_items,
            stringResource(R1.string.movies)
        )
    )
}

@Composable
fun TrendingTVShowScreen(
    navController: NavController,
    viewModel: TrendingTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.trending,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun PopularTVShowScreen(
    navController: NavController,
    viewModel: PopularTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.popular,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun AiringTodayTVShowScreen(
    navController: NavController,
    viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.airing_today,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun OnTheAirTVShowScreen(
    navController: NavController,
    viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.on_the_air,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun TopRatedTVShowScreen(
    navController: NavController,
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.highest_rate,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun DiscoverTVShowScreen(
    navController: NavController,
    viewModel: DiscoverTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.discover,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
fun SimilarTVShowScreen(
    navController: NavController,
    viewModel: SimilarTvSeriesViewModel = hiltViewModel()
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.similar_items,
            stringResource(R1.string.tv_series)
        )
    )
}

@Composable
private fun MoviePagingScreen(
    viewModel: BasePagingViewModel<Movie>,
    navController: NavController,
    title: String
) {
    PagingScreen(
        viewModel = viewModel,
        navController = navController,
        onClick = { navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        onSearchClicked = { navController.navigate(MainDestinations.TMDB_SEARCH_MOVIE_ROUTE) },
        title = title
    )
}

@Composable
private fun TVShowPagingScreen(
    viewModel: BasePagingViewModel<TVShow>,
    navController: NavController,
    title: String
) {
    PagingScreen(
        viewModel = viewModel,
        navController = navController,
        onClick = { navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        onSearchClicked = { navController.navigate(MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE) },
        title = title
    )
}

@Composable
private fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    navController: NavController,
    onClick: (TMDbItem) -> Unit,
    onSearchClicked: () -> Unit,
    title: String,
) {
    Box {
        PagingScreen(viewModel, onClick)
        DestinationBar(
            title = title,
            upPress = { navController.navigateUp() },
            onSearchClicked = onSearchClicked
        )
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