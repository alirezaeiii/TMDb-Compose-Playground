package com.sample.tmdb.paging.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.paging.BasePagingViewModel
import com.sample.tmdb.paging.PagingScreen
import com.sample.tmdb.paging.R
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

@Composable
fun TrendingMovieScreen(
    viewModel: TrendingMoviesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.trending,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun PopularMovieScreen(viewModel: PopularMoviesViewModel, onNavigate: (TMDbNavKey) -> Unit, onNavigateUp: () -> Unit) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.popular,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun NowPlayingMovieScreen(
    viewModel: NowPlayingMoviesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.now_playing,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun UpcomingMovieScreen(
    viewModel: UpcomingMoviesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.upcoming,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun TopRatedMovieScreen(
    viewModel: TopRatedMoviesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.highest_rate,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun DiscoverMovieScreen(
    viewModel: DiscoverMoviesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.discover,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun SimilarMovieScreen(viewModel: SimilarMoviesViewModel, onNavigate: (TMDbNavKey) -> Unit, onNavigateUp: () -> Unit) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.similar_items,
            stringResource(com.sample.tmdb.common.R.string.movies),
        ),
    )
}

@Composable
fun TrendingTVShowScreen(
    viewModel: TrendingTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.trending,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
fun PopularTVShowScreen(
    viewModel: PopularTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.popular,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
fun AiringTodayTVShowScreen(
    viewModel: AiringTodayTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.airing_today,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
fun OnTheAirTVShowScreen(
    viewModel: OnTheAirTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.on_the_air,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
fun TopRatedTVShowScreen(
    viewModel: TopRatedTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.highest_rate,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
fun DiscoverTVShowScreen(
    viewModel: DiscoverTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.discover,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
fun SimilarTVShowScreen(
    viewModel: SimilarTvSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    PagingScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        title =
        stringResource(
            R.string.similar_items,
            stringResource(com.sample.tmdb.common.R.string.tv_series),
        ),
    )
}

@Composable
private fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
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
