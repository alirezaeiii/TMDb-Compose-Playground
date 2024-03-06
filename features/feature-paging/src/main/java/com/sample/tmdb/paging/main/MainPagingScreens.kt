package com.sample.tmdb.paging.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
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
    navController: NavController,
    viewModel: TrendingMoviesViewModel = hiltViewModel(),
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title = stringResource(
            R.string.trending,
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.movies)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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
            stringResource(com.sample.tmdb.common.R.string.tv_series)
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