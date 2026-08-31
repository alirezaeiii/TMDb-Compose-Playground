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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.tmdb.R
import com.sample.tmdb.bookmark.BookmarkScreen
import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Dimens.TMDb_0_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.theme.AlphaNavigationBar
import com.sample.tmdb.credit.CreditScreen
import com.sample.tmdb.detail.MovieDetailScreen
import com.sample.tmdb.detail.MovieDetailViewModel
import com.sample.tmdb.detail.TVShowDetailScreen
import com.sample.tmdb.detail.TVShowDetailViewModel
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.feed.MovieFeedScreen
import com.sample.tmdb.feed.TVShowFeedScreen
import com.sample.tmdb.gallery.ImagesScreen
import com.sample.tmdb.paging.main.AiringTodayTVShowScreen
import com.sample.tmdb.paging.main.DiscoverMovieScreen
import com.sample.tmdb.paging.main.DiscoverTVShowScreen
import com.sample.tmdb.paging.main.NowPlayingMovieScreen
import com.sample.tmdb.paging.main.OnTheAirTVShowScreen
import com.sample.tmdb.paging.main.PopularMovieScreen
import com.sample.tmdb.paging.main.PopularTVShowScreen
import com.sample.tmdb.paging.main.SimilarMovieScreen
import com.sample.tmdb.paging.main.SimilarTVShowScreen
import com.sample.tmdb.paging.main.TopRatedMovieScreen
import com.sample.tmdb.paging.main.TopRatedTVShowScreen
import com.sample.tmdb.paging.main.TrendingMovieScreen
import com.sample.tmdb.paging.main.TrendingTVShowScreen
import com.sample.tmdb.paging.main.UpcomingMovieScreen
import com.sample.tmdb.paging.main.movie.SimilarMoviesViewModel
import com.sample.tmdb.paging.main.tvshow.SimilarTvSeriesViewModel
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

    val entryProvider = remember {
        entryProvider<TMDbNavKey> {
            entry<TMDbNavKey.Movie> {
                MovieFeedScreen(
                    hiltViewModel(),
                    languageViewModel,
                    { appState.navigator.navigate(TMDbNavKey.SearchMovies) },
                    { appState.navigator.navigate(TMDbNavKey.MovieDetail(it.id)) },
                    { route ->
                        val navKey = when (route) {
                            "trending_movies" -> TMDbNavKey.TrendingMovies
                            "popular_movies" -> TMDbNavKey.PopularMovies
                            "now_playing_movies" -> TMDbNavKey.NowPlayingMovies
                            "upcoming_movies" -> TMDbNavKey.UpcomingMovies
                            "top_rated_movies" -> TMDbNavKey.TopRatedMovies
                            "discover_movies" -> TMDbNavKey.DiscoverMovies
                            else -> TMDbNavKey.Movie
                        }
                        appState.navigator.navigate(navKey)
                    },
                    scaffoldState,
                )
            }
            entry<TMDbNavKey.TvShow> {
                TVShowFeedScreen(
                    hiltViewModel(),
                    languageViewModel,
                    { appState.navigator.navigate(TMDbNavKey.SearchTvShows) },
                    { appState.navigator.navigate(TMDbNavKey.TvShowDetail(it.id)) },
                    { route ->
                        val navKey = when (route) {
                            "trending_tv_show" -> TMDbNavKey.TrendingTvShows
                            "popular_tv_show" -> TMDbNavKey.PopularTvShows
                            "airing_today_tv_show" -> TMDbNavKey.AiringTodayTvShows
                            "on_the_air_tv_show" -> TMDbNavKey.OnTheAirTvShows
                            "top_rated_tv_show" -> TMDbNavKey.TopRatedTvShows
                            "discover_tv_show" -> TMDbNavKey.DiscoverTvShows
                            else -> TMDbNavKey.TvShow
                        }
                        appState.navigator.navigate(navKey)
                    },
                    scaffoldState,
                )
            }
            entry<TMDbNavKey.Bookmark> {
                BookmarkScreen(
                    hiltViewModel(),
                    hiltViewModel(),
                    languageViewModel,
                    { appState.navigator.navigate(TMDbNavKey.MovieDetail(it.id)) },
                    { appState.navigator.navigate(TMDbNavKey.TvShowDetail(it.id)) },
                    scaffoldState,
                )
            }
            entry<TMDbNavKey.Setting> {
                SettingsScreen(languageViewModel)
            }
            entry<TMDbNavKey.MovieDetail> { key ->
                MovieDetailScreen(
                    hiltViewModel<MovieDetailViewModel, MovieDetailViewModel.Factory>(
                        key = "MovieDetail_${key.id}",
                        creationCallback = { factory -> factory.create(key.id) },
                    ),
                    { appState.navigator.navigate(TMDbNavKey.MovieDetail(it.id)) },
                    { appState.navigator.navigate(TMDbNavKey.SimilarMovies(it)) },
                    { person -> appState.navigator.navigate(TMDbNavKey.Person(person.id as Int)) },
                    { images, index ->
                        appState.navigator.navigate(
                            TMDbNavKey.Images(
                                gson.toJson(images, object : TypeToken<List<TMDbImage>>() {}.type),
                                index,
                            ),
                        )
                    },
                    { cast ->
                        appState.navigator.navigate(
                            TMDbNavKey.Cast(gson.toJson(cast, object : TypeToken<List<Cast>>() {}.type)),
                        )
                    },
                    { crew ->
                        appState.navigator.navigate(
                            TMDbNavKey.Crew(gson.toJson(crew, object : TypeToken<List<Crew>>() {}.type)),
                        )
                    },
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.TvShowDetail> { key ->
                TVShowDetailScreen(
                    hiltViewModel<TVShowDetailViewModel, TVShowDetailViewModel.Factory>(
                        key = "TvShowDetail_${key.id}",
                        creationCallback = { factory -> factory.create(key.id) },
                    ),
                    { appState.navigator.navigate(TMDbNavKey.TvShowDetail(it.id)) },
                    { appState.navigator.navigate(TMDbNavKey.SimilarTvShows(it)) },
                    { person -> appState.navigator.navigate(TMDbNavKey.Person(person.id as Int)) },
                    { images, index ->
                        appState.navigator.navigate(
                            TMDbNavKey.Images(
                                gson.toJson(images, object : TypeToken<List<TMDbImage>>() {}.type),
                                index,
                            ),
                        )
                    },
                    { cast ->
                        appState.navigator.navigate(
                            TMDbNavKey.Cast(gson.toJson(cast, object : TypeToken<List<Cast>>() {}.type)),
                        )
                    },
                    { crew ->
                        appState.navigator.navigate(
                            TMDbNavKey.Crew(gson.toJson(crew, object : TypeToken<List<Crew>>() {}.type)),
                        )
                    },
                    appState.navigator::goBack,
                )
            }
            val onClickedMovie: (TMDbItem) -> Unit =
                { appState.navigator.navigate(TMDbNavKey.MovieDetail(it.id)) }
            val onSearchedClickedMovie: () -> Unit = { appState.navigator.navigate(TMDbNavKey.SearchMovies) }
            entry<TMDbNavKey.TrendingMovies> {
                TrendingMovieScreen(hiltViewModel(), onClickedMovie, onSearchedClickedMovie, appState.navigator::goBack)
            }
            entry<TMDbNavKey.PopularMovies> {
                PopularMovieScreen(hiltViewModel(), onClickedMovie, onSearchedClickedMovie, appState.navigator::goBack)
            }
            entry<TMDbNavKey.NowPlayingMovies> {
                NowPlayingMovieScreen(
                    hiltViewModel(),
                    onClickedMovie,
                    onSearchedClickedMovie,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.UpcomingMovies> {
                UpcomingMovieScreen(hiltViewModel(), onClickedMovie, onSearchedClickedMovie, appState.navigator::goBack)
            }
            entry<TMDbNavKey.TopRatedMovies> {
                TopRatedMovieScreen(hiltViewModel(), onClickedMovie, onSearchedClickedMovie, appState.navigator::goBack)
            }
            entry<TMDbNavKey.DiscoverMovies> {
                DiscoverMovieScreen(hiltViewModel(), onClickedMovie, onSearchedClickedMovie, appState.navigator::goBack)
            }
            entry<TMDbNavKey.SimilarMovies> { key ->
                SimilarMovieScreen(
                    hiltViewModel<SimilarMoviesViewModel, SimilarMoviesViewModel.Factory>(
                        key = "SimilarMovies_${key.id}",
                        creationCallback = { factory -> factory.create(key.id) },
                    ),
                    onClickedMovie,
                    onSearchedClickedMovie,
                    appState.navigator::goBack,
                )
            }
            val onClickedTvShow: (TMDbItem) -> Unit =
                { appState.navigator.navigate(TMDbNavKey.TvShowDetail(it.id)) }
            val onSearchClickedTvShow: () -> Unit = { appState.navigator.navigate(TMDbNavKey.SearchTvShows) }
            entry<TMDbNavKey.TrendingTvShows> {
                TrendingTVShowScreen(
                    hiltViewModel(),
                    onClickedTvShow,
                    onSearchClickedTvShow,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.PopularTvShows> {
                PopularTVShowScreen(hiltViewModel(), onClickedTvShow, onSearchClickedTvShow, appState.navigator::goBack)
            }
            entry<TMDbNavKey.AiringTodayTvShows> {
                AiringTodayTVShowScreen(
                    hiltViewModel(),
                    onClickedTvShow,
                    onSearchClickedTvShow,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.OnTheAirTvShows> {
                OnTheAirTVShowScreen(
                    hiltViewModel(),
                    onClickedTvShow,
                    onSearchClickedTvShow,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.TopRatedTvShows> {
                TopRatedTVShowScreen(
                    hiltViewModel(),
                    onClickedTvShow,
                    onSearchClickedTvShow,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.DiscoverTvShows> {
                DiscoverTVShowScreen(
                    hiltViewModel(),
                    onClickedTvShow,
                    onSearchClickedTvShow,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.SimilarTvShows> { key ->
                SimilarTVShowScreen(
                    hiltViewModel<SimilarTvSeriesViewModel, SimilarTvSeriesViewModel.Factory>(
                        key = "SimilarTvShows_${key.id}",
                        creationCallback = { factory -> factory.create(key.id) },
                    ),
                    onClickedTvShow,
                    onSearchClickedTvShow,
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.SearchMovies> {
                SearchMoviesScreen(
                    hiltViewModel(),
                    { appState.navigator.navigate(TMDbNavKey.MovieDetail(it.id)) },
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.SearchTvShows> {
                SearchTVSeriesScreen(
                    hiltViewModel(),
                    { appState.navigator.navigate(TMDbNavKey.TvShowDetail(it.id)) },
                    appState.navigator::goBack,
                )
            }
            val navigateToPerson: (person: Credit) -> Unit =
                { person -> appState.navigator.navigate(TMDbNavKey.Person(person.id as Int)) }
            entry<TMDbNavKey.Cast> { key ->
                CreditScreen(
                    R.string.cast,
                    appState.navigator::goBack,
                    navigateToPerson,
                    gson.fromJson<List<Cast>>(
                        key.creditsJson,
                        object : TypeToken<List<Cast>>() {}.type,
                    ),
                )
            }
            entry<TMDbNavKey.Crew> { key ->
                CreditScreen(
                    R.string.crew,
                    appState.navigator::goBack,
                    navigateToPerson,
                    gson.fromJson<List<Crew>>(
                        key.creditsJson,
                        object : TypeToken<List<Crew>>() {}.type,
                    ),
                )
            }
            entry<TMDbNavKey.Person> { key ->
                PersonScreen(
                    hiltViewModel<PersonViewModel, PersonViewModel.Factory>(
                        key = "Person_${key.id}",
                        creationCallback = { factory -> factory.create(key.id) },
                    ),
                    appState.navigator::goBack,
                )
            }
            entry<TMDbNavKey.Images> { key ->
                ImagesScreen(
                    images = gson.fromJson(
                        key.imagesJson,
                        object : TypeToken<List<TMDbImage>>() {}.type,
                    ),
                    initialPage = key.initialPage,
                )
            }
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
    MOVIE_SECTION(TMDbNavKey.Movie, R.string.movie, Icons.Outlined.Movie, Icons.Filled.Movie),
    TV_SHOW_SECTION(TMDbNavKey.TvShow, R.string.tv_show, Icons.Outlined.Tv, Icons.Filled.Tv),
    BOOKMARK_SECTION(TMDbNavKey.Bookmark, R.string.favorite, Icons.Outlined.Favorite, Icons.Filled.Favorite),
    SETTING_SECTION(TMDbNavKey.Setting, R.string.setting, Icons.Outlined.Settings, Icons.Filled.Settings),
}

private val gson = Gson()
