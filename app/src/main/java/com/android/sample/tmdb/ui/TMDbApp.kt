package com.android.sample.tmdb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.android.sample.tmdb.R
import com.android.sample.tmdb.ui.detail.MovieDetailScreenContent
import com.android.sample.tmdb.ui.detail.TVShowDetailScreenContent
import com.android.sample.tmdb.ui.feed.FeedMovieScreen
import com.android.sample.tmdb.ui.feed.FeedTVShowScreen
import com.android.sample.tmdb.ui.feed.TMDbBottomBar

@Composable
fun TMDbApp() {
    val appState = rememberTMDbkAppState()
    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                TMDbBottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute!!,
                    navigateToRoute = appState::navigateToBottomBarRoute
                )
            }
        }
    ) { innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = MainDestinations.HOME_ROUTE,
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            tmdbNavGraph(
                onMovieSelected = appState::navigateToMovieDetail,
                onTVShowSelected = appState::navigateToTVShowDetail
            )
        }
    }
}

private fun NavGraphBuilder.tmdbNavGraph(
    onMovieSelected: (Int, NavBackStackEntry) -> Unit,
    onTVShowSelected: (Int, NavBackStackEntry) -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.MOVIE_SECTION.route
    ) {
        composable(route = HomeSections.MOVIE_SECTION.route) { from ->
            FeedMovieScreen(onClick = {
                onMovieSelected(it.id, from)
            })
        }
        composable(route = HomeSections.TV_SHOW_SECTION.route) { from ->
            FeedTVShowScreen(onClick = {
                onTVShowSelected(it.id, from)
            })
        }
    }
    composable(
        route = "${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
    ) {
        MovieDetailScreenContent()
    }
    composable(
        route = "${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
    ) {
        TVShowDetailScreenContent()
    }
}

enum class HomeSections(val route: String, val title: String, val icon: Int) {
    MOVIE_SECTION("Movie", "Movie", R.drawable.ic_movie),
    TV_SHOW_SECTION("TVShow","TVShow", R.drawable.ic_tv_series)
}