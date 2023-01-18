package com.android.sample.tmdb.ui.paging

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.domain.model.TMDbType
import com.android.sample.tmdb.ui.paging.main.movie.*
import com.android.sample.tmdb.ui.paging.search.Search
import com.android.sample.tmdb.ui.paging.search.SearchMoviesViewModel
import com.android.sample.tmdb.ui.paging.search.SearchTvSeriesViewModel
import com.android.sample.tmdb.ui.paging.main.tvshow.*

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
fun SearchMoviesScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    viewModel: SearchMoviesViewModel = hiltViewModel()
) {
    Search(
        viewModel = viewModel,
        onClick = onClick,
        upPress = upPress,
        resourceId = R.string.movies
    )
}

@Composable
fun SearchTVSeriesScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    viewModel: SearchTvSeriesViewModel = hiltViewModel()
) {
    Search(
        viewModel = viewModel,
        onClick = onClick,
        upPress = upPress,
        resourceId = R.string.tv_series
    )
}