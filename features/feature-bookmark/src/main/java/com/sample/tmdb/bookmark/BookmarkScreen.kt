package com.sample.tmdb.bookmark

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_48_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_96_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.component.TMDbContent
import com.sample.tmdb.common.ui.component.TMDbDivider
import com.sample.tmdb.common.ui.component.TMDbGrid
import com.sample.tmdb.common.ui.component.TMDbSwipeRefresh
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque
import com.sample.tmdb.common.utils.fullSpanGridItem
import com.sample.tmdb.common.utils.navigationBarPadding
import com.sample.tmdb.navigation.TMDbNavKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BookmarkScreen(
    movieViewModel: BookmarkMovieViewModel,
    tvShowViewModel: BookmarkTVShowViewModel,
    languageViewModel: LanguageViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    scaffoldState: ScaffoldState,
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val tabs = remember { MediaTab.entries.toTypedArray() }
    val pagerState =
        rememberPagerState(
            pageCount = {
                tabs.size
            },
        )
    val selectedTabIndex = pagerState.currentPage

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { page ->
            when (page) {
                MediaTab.Movies.ordinal -> MoviesTabContent(
                    movieViewModel,
                    languageViewModel,
                    onNavigate,
                    scaffoldState,
                )

                MediaTab.TVShows.ordinal -> TVShowsTabContent(
                    tvShowViewModel,
                    languageViewModel,
                    onNavigate,
                    scaffoldState,
                )
            }
        }
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque),
            divider = { TMDbDivider() },
            modifier = Modifier
                .statusBarsPadding(),
        ) {
            tabs.forEach { tab ->
                val index = tab.ordinal
                val selected = selectedTabIndex == index
                Tab(
                    selected = selected,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = {
                        Text(
                            text = stringResource(id = tab.titleResourceId),
                            style = MaterialTheme.typography.subtitle1,
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun MoviesTabContent(
    viewModel: BookmarkMovieViewModel,
    languageViewModel: LanguageViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    scaffoldState: ScaffoldState,
) {
    TabContent(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        onNavigate = onNavigate,
        textResourceId = commonR.string.movies,
        scaffoldState = scaffoldState,
    )
}

@Composable
private fun TVShowsTabContent(
    viewModel: BookmarkTVShowViewModel,
    languageViewModel: LanguageViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    scaffoldState: ScaffoldState,
) {
    TabContent(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        onNavigate = onNavigate,
        textResourceId = commonR.string.tv_series,
        scaffoldState = scaffoldState,
    )
}

@Composable
private fun <T : TMDbItem> TabContent(
    viewModel: BaseBookmarkViewModel<T>,
    languageViewModel: LanguageViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    @StringRes textResourceId: Int,
    scaffoldState: ScaffoldState,
) {
    viewModel.refresh()
    Content(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        onNavigate = onNavigate,
        scaffoldState = scaffoldState,
    ) { state, items ->
        TMDbSwipeRefresh(
            state.isRefreshing,
            { viewModel.refresh(true) },
            WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + TMDb_48_dp,
        ) {
            if (items.isEmpty()) {
                EmptyView(textResourceId = textResourceId)
            } else {
                TabContent(items = items, onClick = viewModel::onTMDbItemClick)
            }
        }
    }
}

@Composable
fun TabContent(items: List<TMDbItem>, onClick: (TMDbItem) -> Unit) {
    TMDbGrid(
        contentPadding =
            PaddingValues(
                top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + TMDb_48_dp,
                start = TMDb_8_dp,
                end = TMDb_8_dp,
            ),
        content = {
            items(items.size) { index ->
                TMDbContent(items[index], onClick)
            }
            fullSpanGridItem {
                Spacer(Modifier.height(navigationBarPadding() + TMDb_96_dp))
            }
        },
    )
}

@Composable
fun EmptyView(@StringRes textResourceId: Int) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (shouldShowEmptyImage()) {
            Image(
                modifier = Modifier.padding(bottom = TMDb_16_dp),
                painter = painterResource(id = R.drawable.ic_empty),
                contentDescription = stringResource(id = R.string.empty_list),
            )
        }
        Text(
            text =
                stringResource(
                    id = R.string.empty_list,
                    stringResource(id = textResourceId),
                ),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun shouldShowEmptyImage(): Boolean {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val isTablet = booleanResource(id = R.bool.is_tablet)
    return isPortrait || isTablet
}

enum class MediaTab(@StringRes val titleResourceId: Int) {
    Movies(titleResourceId = R.string.movie),
    TVShows(titleResourceId = R.string.tv_show),
}
