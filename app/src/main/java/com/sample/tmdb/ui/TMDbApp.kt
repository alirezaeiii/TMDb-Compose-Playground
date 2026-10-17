package com.sample.tmdb.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sample.tmdb.R
import com.sample.tmdb.bookmark.BookmarkScreen
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.ui.Dimens.TMDb_48_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_64_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.theme.AlphaNavigationBar
import com.sample.tmdb.credit.CreditScreen
import com.sample.tmdb.credit.CreditSource
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
import com.sample.tmdb.person.paging.PagingPersonScreen
import com.sample.tmdb.person.paging.SearchPagingScreen
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
        entry<TMDbNavKey.People> {
            CreditScreen(
                source = CreditSource.FromViewModel(hiltViewModel(), onNavigate),
                resourceId = commonR.string.trending_people,
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
                source = CreditSource.FromJson(
                    key.creditsJson,
                    onNavigateUp,
                    onPersonClicked = { person -> onNavigate(TMDbNavKey.Person(person.id)) },
                ),
                resourceId = R.string.cast,
            )
        }
        entry<TMDbNavKey.Crew> { key ->
            CreditScreen(
                source = CreditSource.FromJson(
                    key.creditsJson,
                    onNavigateUp,
                    onPersonClicked = { person -> onNavigate(TMDbNavKey.Person(person.id)) },
                ),
                resourceId = R.string.crew,
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
        entry<TMDbNavKey.PersonPaging> {
            PagingPersonScreen(
                viewModel = hiltViewModel(),
                onNavigate,
                onNavigateUp,
            )
        }
        entry<TMDbNavKey.SearchPerson> {
            SearchPagingScreen(
                hiltViewModel(),
                onNavigate,
                onNavigateUp,
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
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 4.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.24f),
                            Color.Black.copy(alpha = 0.12f),
                            Color.Black.copy(alpha = 0.03f),
                            Color.Transparent,
                        ),
                    ),
                    shape = RoundedCornerShape(28.dp),
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(
                    MaterialTheme.colors.background.copy(
                        alpha = AlphaNavigationBar,
                    ),
                ),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TMDb_64_dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEach { section ->
                    val selected = section.navKey == currentRoute

                    val weight by animateFloatAsState(
                        targetValue = if (selected) 2f else 1f,
                        animationSpec = tween(durationMillis = 500),
                        label = "tabWeight",
                    )

                    Box(
                        modifier = Modifier.weight(weight),
                        contentAlignment = Alignment.Center,
                    ) {
                        TMDbBottomBarItem(
                            section = section,
                            selected = selected,
                            onClick = { navigateToRoute(section.navKey) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TMDbBottomBarItem(section: HomeSections, selected: Boolean, onClick: () -> Unit) {
    val contentColor = if (selected) {
        MaterialTheme.colors.onBackground
    } else {
        MaterialTheme.colors.onBackground.copy(
            alpha = ContentAlpha.disabled,
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
            .height(TMDb_48_dp)
            .clip(RoundedCornerShape(50))
            .then(
                if (selected) {
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(50),
                    )
                } else {
                    Modifier
                },
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(50),
        color = if (selected) {
            MaterialTheme.colors.primary.copy(alpha = 0.06f)
        } else {
            Color.Transparent
        },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = if (selected) {
                    section.selectedIcon
                } else {
                    section.unselectedIcon
                },
                contentDescription = stringResource(section.title),
                tint = contentColor,
                modifier = Modifier.size(24.dp),
            )

            AnimatedVisibility(visible = selected) {
                Row {
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = stringResource(section.title),
                        color = contentColor,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                    )
                }
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
    PEOPLE_SECTION(TMDbNavKey.People, R.string.people, Icons.Outlined.People, Icons.Filled.People),
    SETTING_SECTION(TMDbNavKey.Setting, R.string.setting, Icons.Outlined.Settings, Icons.Filled.Settings),
}
