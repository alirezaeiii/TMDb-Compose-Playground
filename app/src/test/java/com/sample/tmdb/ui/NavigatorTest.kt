package com.sample.tmdb.ui

import androidx.compose.runtime.mutableStateOf
import androidx.navigation3.runtime.NavBackStack
import com.sample.tmdb.navigation.TMDbNavKey
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NavigatorTest {

    private lateinit var navigationState: NavigationState
    private lateinit var navigator: Navigator

    private val startRoute = TMDbNavKey.MovieNav
    private val tvShowRoute = TMDbNavKey.TvShowNav
    private val bookmarkRoute = TMDbNavKey.Bookmark

    @Before
    fun setup() {
        val topLevelRoutes = setOf(startRoute, tvShowRoute, bookmarkRoute)
        val backStacks = topLevelRoutes.associateWith { key -> NavBackStack(key) }
        navigationState = NavigationState(
            startRoute = startRoute,
            topLevelRoute = mutableStateOf(startRoute),
            backStacks = backStacks,
        )
        navigator = Navigator(navigationState)
    }

    @Test
    fun `goBack returns false when at start route base`() {
        // Given navigator is at startRoute base (MovieNav)
        assertEquals(startRoute, navigationState.topLevelRoute)

        // When goBack is called
        val result = navigator.goBack()

        // Then it returns false indicating no route left in stack (exit app)
        assertFalse(result)
        assertEquals(startRoute, navigationState.topLevelRoute)
    }

    @Test
    fun `goBack switches to start route when at base of another top level route`() {
        // Given navigator navigated to TvShowNav tab
        navigator.navigate(tvShowRoute)
        assertEquals(tvShowRoute, navigationState.topLevelRoute)

        // When goBack is called
        val result = navigator.goBack()

        // Then it returns true and switches to startRoute
        assertTrue(result)
        assertEquals(startRoute, navigationState.topLevelRoute)
    }

    @Test
    fun `goBack pops current stack when on nested route`() {
        // Given navigator is at MovieNav and navigates to MovieDetail
        val detailRoute = TMDbNavKey.MovieDetail(123)
        navigator.navigate(detailRoute)

        val movieStack = navigationState.backStacks[startRoute]!!
        assertEquals(2, movieStack.size)
        assertEquals(detailRoute, movieStack.last())

        // When goBack is called
        val result = navigator.goBack()

        // Then it returns true and pops detail route
        assertTrue(result)
        assertEquals(1, movieStack.size)
        assertEquals(startRoute, movieStack.last())
    }

    @Test
    fun `goBack from start route base after returning from another tab returns false`() {
        // Given navigator goes to TvShowNav and back to MovieNav
        navigator.navigate(tvShowRoute)
        navigator.goBack()
        assertEquals(startRoute, navigationState.topLevelRoute)

        // When goBack is called again
        val result = navigator.goBack()

        // Then it returns false (exit app)
        assertFalse(result)
    }
}
