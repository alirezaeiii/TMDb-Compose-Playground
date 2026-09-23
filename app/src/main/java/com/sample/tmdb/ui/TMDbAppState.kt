package com.sample.tmdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.sample.tmdb.common.ui.TMDbNavKey

/**
 * Remembers and creates an instance of [TMDbAppState]
 */
@Composable
fun rememberTMDbAppState(
    navigationState: NavigationState = rememberNavigationState(
        startRoute = Movie,
        topLevelRoutes = setOf(Movie, TvShow, Bookmark, Setting),
    ),
) = remember(navigationState) {
    TMDbAppState(navigationState)
}

@Stable
class TMDbAppState(val navigationState: NavigationState) {
    val navigator = Navigator(navigationState)

    val bottomBarTabs = HomeSections.entries.toTypedArray()
    private val bottomBarRoutes = bottomBarTabs.map { it.navKey }

    val shouldShowBottomBar: Boolean
        get() = currentRoute in bottomBarRoutes

    val currentRoute: TMDbNavKey
        get() = navigationState.backStacks[navigationState.topLevelRoute]?.last()
            ?: navigationState.topLevelRoute

    fun navigateToBottomBarRoute(navKey: TMDbNavKey) {
        navigator.navigate(navKey)
    }
}
