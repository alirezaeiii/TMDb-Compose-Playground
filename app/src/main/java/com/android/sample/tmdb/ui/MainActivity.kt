package com.android.sample.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.sample.tmdb.ui.feed.FeedMovieScreen
import com.android.sample.tmdb.ui.feed.FeedTVShowScreen
import com.android.sample.tmdb.ui.theme.TmdbPagingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbPagingComposeTheme {
                MainScreenView()
            }
        }
    }

    @Composable
    private fun MainScreenView(){
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { val items = listOf(
                BottomNavItem.Movie,
                BottomNavItem.TVShow
            )
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.surface,
                    contentColor = MaterialTheme.colors.onSurface
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                            label = { Text(text = item.title,
                                fontSize = TmdbPagingComposeTheme.fontSizes.sp_11) },
                            selectedContentColor = MaterialTheme.colors.onSurface,
                            unselectedContentColor = MaterialTheme.colors.onSurface.copy(0.4f),
                            alwaysShowLabel = true,
                            selected = currentRoute == item.title,
                            onClick = {
                                navController.navigate(item.title) {

                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                } }
        ) {
            NavigationGraph(navController = navController, it.calculateBottomPadding())
        }
    }

    @Composable
    private fun NavigationGraph(navController: NavHostController, bottomPadding: Dp) {
        NavHost(navController, startDestination = BottomNavItem.Movie.title) {
            composable(BottomNavItem.Movie.title) {
                FeedMovieScreen(navController, bottomPadding)
            }
            composable(BottomNavItem.TVShow.title) {
                FeedTVShowScreen(navController, bottomPadding)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun BottomNavigationPreview() {
        MainScreenView()
    }
}