package com.sample.tmdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer

/**
 * Create a navigation state that persists config changes and process death.
 */
@Composable
fun rememberNavigationState(startRoute: TMDbNavKey, topLevelRoutes: Set<TMDbNavKey>): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute,
        topLevelRoutes,
        serializer = MutableStateSerializer(NavKeySerializer()),
    ) {
        mutableStateOf(startRoute)
    }

    val backStacks = topLevelRoutes.associateWith { key -> rememberNavBackStack(key) as NavBackStack<TMDbNavKey> }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks,
        )
    }
}

/**
 * State holder for navigation state.
 *
 * @param startRoute - the start route. The user will exit the app through this route.
 * @param topLevelRoute - the current top level route
 * @param backStacks - the back stacks for each top level route
 */
class NavigationState(
    val startRoute: TMDbNavKey,
    topLevelRoute: MutableState<TMDbNavKey>,
    val backStacks: Map<TMDbNavKey, NavBackStack<TMDbNavKey>>,
) {
    var topLevelRoute: TMDbNavKey by topLevelRoute
    val stacksInUse: List<TMDbNavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }
}

/**
 * Convert NavigationState into NavEntries.
 */
@Composable
fun NavigationState.toEntries(
    entryProvider: (TMDbNavKey) -> NavEntry<TMDbNavKey>,
): List<NavEntry<TMDbNavKey>> {
    val entryCache = remember { mutableMapOf<TMDbNavKey, NavEntry<TMDbNavKey>>() }

    return stacksInUse.flatMap { key ->
        val stack = backStacks[key] ?: emptyList()

        stack.map { navKey ->
            entryCache.getOrPut(navKey) {
                entryProvider(navKey)
            }
        }
    }
}
