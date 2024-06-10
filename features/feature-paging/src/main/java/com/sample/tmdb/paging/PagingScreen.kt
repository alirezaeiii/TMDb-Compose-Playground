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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
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
    if (lazyTMDbItems.itemCount == 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            NoDataFoundAnimation(
                modifier = Modifier
                    .size(200.dp)
            )
        }
    } else {
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
}

@Composable
fun NoDataFoundAnimation(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.no_data_found
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}