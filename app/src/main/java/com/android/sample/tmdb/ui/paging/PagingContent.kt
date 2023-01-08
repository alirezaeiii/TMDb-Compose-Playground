package com.android.sample.tmdb.ui.paging

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.paging.movie.*
import com.android.sample.tmdb.ui.paging.tvshow.*

@Composable
fun TrendingMovieScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: TrendingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun PopularMovieScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun NowPlayingMovieScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: NowPlayingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun UpcomingMovieScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun TopRatedMovieScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun TrendingTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: TrendingTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun PopularTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: PopularTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun AiringTodayTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun OnTheAirTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}

@Composable
fun TopRatedTVShowScreen(
    onClick: (TMDbItem) -> Unit,
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel, onClick = onClick)
}