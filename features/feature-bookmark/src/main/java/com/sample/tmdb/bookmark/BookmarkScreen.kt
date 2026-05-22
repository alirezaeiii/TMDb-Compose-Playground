package com.sample.tmdb.bookmark

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens.TMDb_104_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_56_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.component.TMDbContent
import com.sample.tmdb.common.ui.component.TMDbDivider
import com.sample.tmdb.common.ui.component.TMDbSwipeRefresh
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque
import com.sample.tmdb.common.utils.navigationBarPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BookmarkScreen(
    movieViewModel: BookmarkMovieViewModel,
    tvShowViewModel: BookmarkTVShowViewModel,
    languageViewModel: LanguageViewModel,
    onMovieClicked: (TMDbItem) -> Unit,
    onTVShowClicked: (TMDbItem) -> Unit,
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
                    onMovieClicked,
                    scaffoldState,
                )

                MediaTab.TVShows.ordinal -> TVShowsTabContent(
                    tvShowViewModel,
                    languageViewModel,
                    onTVShowClicked,
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
    onClick: (TMDbItem) -> Unit,
    scaffoldState: ScaffoldState,
) {
    TabContent(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        onClick = onClick,
        textResourceId = commonR.string.movies,
        scaffoldState = scaffoldState,
    )
}

@Composable
private fun TVShowsTabContent(
    viewModel: BookmarkTVShowViewModel,
    languageViewModel: LanguageViewModel,
    onClick: (TMDbItem) -> Unit,
    scaffoldState: ScaffoldState,
) {
    TabContent(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        onClick = onClick,
        textResourceId = commonR.string.tv_series,
        scaffoldState = scaffoldState,
    )
}

@Composable
private fun <T : TMDbItem> TabContent(
    viewModel: BaseViewModel<List<T>, Nothing>,
    languageViewModel: LanguageViewModel,
    onClick: (TMDbItem) -> Unit,
    @StringRes textResourceId: Int,
    scaffoldState: ScaffoldState,
) {
    viewModel.refresh()
    Content(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        scaffoldState = scaffoldState,
    ) { items ->
        TMDbSwipeRefresh(viewModel) {
            if (items.isEmpty()) {
                EmptyView(textResourceId = textResourceId)
            } else {
                TabContent(items = items, onClick = onClick)
            }
        }
    }
}

@Composable
fun TabContent(items: List<TMDbItem>, onClick: (TMDbItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding =
        PaddingValues(
            top = TMDb_104_dp,
            start = TMDb_8_dp,
            end = TMDb_8_dp,
            bottom = navigationBarPadding().plus(TMDb_56_dp),
        ),
        horizontalArrangement =
        Arrangement.spacedBy(
            TMDb_8_dp,
            Alignment.CenterHorizontally,
        ),
        content = {
            items(items.size) { index ->
                TMDbContent(items[index], onClick)
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
