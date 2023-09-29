package com.sample.tmdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * Destinations used in the [TMDbApp].
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val TMDB_MOVIE_DETAIL_ROUTE = "movie"
    const val TMDB_TV_SHOW_DETAIL_ROUTE = "tv_show"
    const val TMDB_ID_KEY = "tmdbId"
    const val TMDB_TRENDING_MOVIES_ROUTE = "trending_movies"
    const val TMDB_POPULAR_MOVIES_ROUTE = "popular_movies"
    const val TMDB_NOW_PLAYING_MOVIES_ROUTE = "now_playing_movies"
    const val TMDB_UPCOMING_MOVIES_ROUTE = "upcoming_movies"
    const val TMDB_TOP_RATED_MOVIES_ROUTE = "top_rated_movies"
    const val TMDB_DISCOVER_MOVIES_ROUTE = "discover_movies"
    const val TMDB_SEARCH_MOVIE_ROUTE = "search_movies"
    const val TMDB_TRENDING_TV_SHOW_ROUTE = "trending_tv_show"
    const val TMDB_POPULAR_TV_SHOW_ROUTE = "popular_tv_show"
    const val TMDB_AIRING_TODAY_TV_SHOW_ROUTE = "airing_today_tv_show"
    const val TMDB_ON_THE_AIR_TV_SHOW_ROUTE = "on_the_air_tv_show"
    const val TMDB_TOP_RATED_TV_SHOW_ROUTE = "top_rated_tv_show"
    const val TMDB_DISCOVER_TV_SHOW_ROUTE = "discover_tv_show"
    const val TMDB_SEARCH_TV_SHOW_ROUTE = "search_tv_show"
    const val TMDB_CAST_ROUTE = "cast_list"
    const val TMDB_CREW_ROUTE = "crew_list"
    const val TMDB_CREDIT_KEY =  "credit_list"
    const val TMDB_PERSON_ROUTE = "person"
    const val TMDB_PERSON_KEY = "personId"
}

/**
 * Remembers and creates an instance of [TMDbAppState]
 */
@Composable
fun rememberTMDbkAppState(
    navController: NavHostController = rememberNavController(),
) =
    remember(navController) {
        TMDbAppState(navController)
    }

@Stable
class TMDbAppState(
    val navController: NavHostController,
) {

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToMovieDetail(id: Int) {
        navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/$id")
    }

    fun navigateToTVShowDetail(id: Int) {
        navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/$id")
    }

    fun navigateToCastList(cast: String) {
        navController.navigate("${MainDestinations.TMDB_CAST_ROUTE}/$cast")
    }

    fun navigateToCrewList(crew: String) {
        navController.navigate("${MainDestinations.TMDB_CREW_ROUTE}/$crew")
    }

    fun navigateToPerson(id: String) {
        navController.navigate("${MainDestinations.TMDB_PERSON_ROUTE}/$id")
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}