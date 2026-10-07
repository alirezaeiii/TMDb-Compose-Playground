package com.sample.tmdb.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sample.tmdb.R
import com.sample.tmdb.bookmark.BookmarkScreen
import com.sample.tmdb.common.ui.Dimens.TMDb_0_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.theme.AlphaNavigationBar
import com.sample.tmdb.credit.CreditScreen
import com.sample.tmdb.detail.MovieDetailScreen
import com.sample.tmdb.detail.MovieDetailViewModel
import com.sample.tmdb.detail.TVShowDetailScreen
import com.sample.tmdb.detail.TVShowDetailViewModel
import com.sample.tmdb.feed.MovieFeedScreen
import com.sample.tmdb.feed.TVShowFeedScreen
import com.sample.tmdb.gallery.ImagesScreen
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.main.MoviePagingScreen
import com.sample.tmdb.paging.main.MoviePagingViewModel
import com.sample.tmdb.paging.main.TVShowPagingScreen
import com.sample.tmdb.paging.main.TvShowPagingViewModel
import com.sample.tmdb.paging.search.SearchMoviesScreen
import com.sample.tmdb.paging.search.SearchTVSeriesScreen
import com.sample.tmdb.preson.PersonScreen
import com.sample.tmdb.preson.PersonViewModel
import com.sample.tmdb.setting.SettingsScreen

@Composable
fun TMDbApp() {
    val appState = rememberTMDbAppState()
    val scaffoldState = rememberScaffoldState()
    val languageViewModel: LanguageViewModel = hiltViewModel()

    val onNavigate: (TMDbNavKey) -> Unit = { appState.navigator.navigate(it) }
    val onNavigateUp: () -> Unit = appState.navigator::goBack

    val entryProvider = entryProvider {
        entry<TMDbNavKey.MovieNav> {
            MovieFeedScreen(
                hiltViewModel(),
                languageViewModel,
                onNavigate,
                scaffoldState,
            )
        }
        entry<TMDbNavKey.TvShowNav> {
            TVShowFeedScreen(
                hiltViewModel(),
                languageViewModel,
                onNavigate,
                scaffoldState,
            )
        }
        entry<TMDbNavKey.Bookmark> {
            BookmarkScreen(
                hiltViewModel(),
                hiltViewModel(),
                languageViewModel,
                onNavigate,
                scaffoldState,
            )
        }
        entry<TMDbNavKey.Setting> {
            SettingsScreen(languageViewModel, hiltViewModel())
        }
        entry<TMDbNavKey.MovieDetail> { key ->
            MovieDetailScreen(
                hiltViewModel<MovieDetailViewModel, MovieDetailViewModel.Factory>(
                    key = "MovieDetail_${key.id}",
                    creationCallback = { factory -> factory.create(key.id) },
                ),
                onNavigate,
                onNavigateUp,
            )
        }
        entry<TMDbNavKey.TvShowDetail> { key ->
            TVShowDetailScreen(
                hiltViewModel<TVShowDetailViewModel, TVShowDetailViewModel.Factory>(
                    key = "TvShowDetail_${key.id}",
                    creationCallback = { factory -> factory.create(key.id) },
                ),
                onNavigate,
                onNavigateUp,
            )
        }

        entry<TMDbNavKey.PagingMovies> { key ->
            MoviePagingScreen(
                hiltViewModel<MoviePagingViewModel, MoviePagingViewModel.Factory>(
                    key = "PagingMovies_${key.id}",
                    creationCallback = { factory ->
                        factory.create(
                            if (key.sortType == SortType.SIMILAR) key.id else null,
                            key.sortType,
                        )
                    },
                ),
                onNavigate,
                onNavigateUp,
                key.sortType,
            )
        }

        entry<TMDbNavKey.PagingTvShows> { key ->
            TVShowPagingScreen(
                hiltViewModel<TvShowPagingViewModel, TvShowPagingViewModel.Factory>(
                    key = "PagingTVShows_${key.id}",
                    creationCallback = { factory ->
                        factory.create(
                            if (key.sortType == SortType.SIMILAR) key.id else null,
                            key.sortType,
                        )
                    },
                ),
                onNavigate,
                onNavigateUp,
                key.sortType,
            )
        }
        entry<TMDbNavKey.SearchMovies> {
            SearchMoviesScreen(hiltViewModel(), onNavigate, onNavigateUp)
        }
        entry<TMDbNavKey.SearchTvShows> {
            SearchTVSeriesScreen(hiltViewModel(), onNavigate, onNavigateUp)
        }
        entry<TMDbNavKey.Cast> { key ->
            CreditScreen(
                resourceId = R.string.cast,
                upPress = onNavigateUp,
                onPersonClicked = { person -> onNavigate(TMDbNavKey.Person(person.id)) },
                creditsJson = key.creditsJson,
            )
        }
        entry<TMDbNavKey.Crew> { key ->
            CreditScreen(
                resourceId = R.string.crew,
                upPress = onNavigateUp,
                onPersonClicked = { person -> onNavigate(TMDbNavKey.Person(person.id)) },
                creditsJson = key.creditsJson,
            )
        }
        entry<TMDbNavKey.Person> { key ->
            PersonScreen(
                hiltViewModel<PersonViewModel, PersonViewModel.Factory>(
                    key = "Person_${key.id}",
                    creationCallback = { factory -> factory.create(key.id) },
                ),
                onNavigateUp,
            )
        }
        entry<TMDbNavKey.Images> { key ->
            ImagesScreen(
                imagesJson = key.imagesJson,
                initialPage = key.initialPage,
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                TMDbBottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute,
                    navigateToRoute = appState::navigateToBottomBarRoute,
                )
            }
        },
    ) { innerPaddingModifier ->
        val newPadding =
            PaddingValues(
                start = innerPaddingModifier.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPaddingModifier.calculateEndPadding(LocalLayoutDirection.current),
                top = innerPaddingModifier.calculateTopPadding(),
                bottom = 0.dp,
            )
        NavDisplay(
            entries = appState.navigationState.toEntries(entryProvider),
            onBack = { appState.navigator.goBack() },
            modifier = Modifier.padding(newPadding),
        )
    }
}

@Composable
private fun TMDbBottomBar(tabs: Array<HomeSections>, currentRoute: TMDbNavKey, navigateToRoute: (TMDbNavKey) -> Unit) {
    Box(
        Modifier.navigationBarsPadding(),
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background.copy(alpha = AlphaNavigationBar),
            elevation = TMDb_0_dp,
        ) {
            tabs.forEach { section ->
                val selected = section.navKey == currentRoute
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(id = section.title))
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) section.selectedIcon else section.unselectedIcon,
                            contentDescription = stringResource(id = section.title),
                        )
                    },
                    selected = selected,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    onClick = { navigateToRoute(section.navKey) },
                )
            }
        }
    }
}

enum class HomeSections(
    val navKey: TMDbNavKey,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
) {
    MOVIE_SECTION(TMDbNavKey.MovieNav, R.string.movie, Icons.Outlined.Movie, Icons.Filled.Movie),
    TV_SHOW_SECTION(TMDbNavKey.TvShowNav, R.string.tv_show, Icons.Outlined.Tv, Icons.Filled.Tv),
    BOOKMARK_SECTION(TMDbNavKey.Bookmark, R.string.favorite, Icons.Outlined.Favorite, Icons.Filled.Favorite),
    SETTING_SECTION(TMDbNavKey.Setting, R.string.setting, Icons.Outlined.Settings, Icons.Filled.Settings),
}
