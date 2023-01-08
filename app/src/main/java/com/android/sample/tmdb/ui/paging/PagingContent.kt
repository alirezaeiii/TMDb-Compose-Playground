package com.android.sample.tmdb.ui.paging

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.ui.paging.movie.*
import com.android.sample.tmdb.ui.paging.tvshow.*

@Composable
fun TrendingMovieScreen(
    viewModel: TrendingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun PopularMovieScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun NowPlayingMovieScreen(
    viewModel: NowPlayingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun UpcomingMovieScreen(
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun TopRatedMovieScreen(
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun TrendingTVShowScreen(
    viewModel: TrendingTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun PopularTVShowScreen(
    viewModel: PopularTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun AiringTodayTVShowScreen(
    viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun OnTheAirTVShowScreen(
    viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun TopRatedTVShowScreen(
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}