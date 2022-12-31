package com.android.sample.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.android.sample.tmdb.ui.detail.DetailScreenContent
import com.android.sample.tmdb.ui.feed.FeedMovieScreen
import com.android.sample.tmdb.ui.feed.TMDbBottomBar
import com.android.sample.tmdb.ui.theme.TmdbPagingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbPagingComposeTheme {
                HomeScreen()
            }
        }
    }

    @Composable
    private fun HomeScreen() {
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
                movieDetailsNavGraph(
                    onSnackSelected = appState::navigateToTMDbDetail
                )
            }
        }
    }

    private fun NavGraphBuilder.movieDetailsNavGraph(
        onSnackSelected: (Int, NavBackStackEntry) -> Unit
    ) {
        navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.MOVIE_SECTION.route
        ) {
            composable(route = HomeSections.MOVIE_SECTION.route) { from ->
                FeedMovieScreen(onClick = {
                    onSnackSelected(it.id, from)
                })
            }
        }
        composable(
            route = "${MainDestinations.TMDB_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
        ) {
            DetailScreenContent()
        }
    }
}