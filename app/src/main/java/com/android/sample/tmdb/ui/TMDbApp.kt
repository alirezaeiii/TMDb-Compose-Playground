package com.android.sample.tmdb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                onTVShowSelected = appState::navigateToTVShowDetail,
                upPress = appState::upPress
            )
        }
    }
}

@Composable
private fun TMDbBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val currentSection = tabs.first { it.route == currentRoute }

    BottomNavigation {
        tabs.forEach { section ->
            val selected = section == currentSection

            BottomNavigationItem(
                label = {
                    Text(text = section.title)
                },
                icon = {
                    Icon(
                        painterResource(id = section.icon),
                        contentDescription = section.title
                    )
                },
                selected = selected,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = { navigateToRoute(section.route) }
            )
        }
    }
}

private fun NavGraphBuilder.tmdbNavGraph(
    onMovieSelected: (Int, NavBackStackEntry) -> Unit,
    onTVShowSelected: (Int, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
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
        MovieDetailScreenContent(upPress)
    }
    composable(
        route = "${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
    ) {
        TVShowDetailScreenContent(upPress)
    }
}

enum class HomeSections(val route: String, val title: String, val icon: Int) {
    MOVIE_SECTION("Movie", "Movie", R.drawable.ic_movie),
    TV_SHOW_SECTION("TVShow","TVShow", R.drawable.ic_tv_series)
}