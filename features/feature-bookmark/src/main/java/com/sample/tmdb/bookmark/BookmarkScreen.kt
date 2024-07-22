package com.sample.tmdb.bookmark

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.component.TMDbDivider
import com.sample.tmdb.common.ui.component.TMDbItemContent
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque
import com.sample.tmdb.common.utils.toDp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.sample.tmdb.common.R as R1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkScreen(
    navController: NavController,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val tabs = remember { MediaTab.values() }
    val pagerState = rememberPagerState(pageCount = {
        tabs.size
    })
    val selectedTabIndex = pagerState.currentPage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque),
            divider = { TMDbDivider() }
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
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                MediaTab.Movies.ordinal -> MoviesTabContent(navController)
                MediaTab.TvShows.ordinal -> TVShowsTabContent(navController)
            }
        }
    }
}

@Composable
private fun MoviesTabContent(
    navController: NavController,
    viewModel: BookmarkMovieViewModel = hiltViewModel()
) {
    TabContent(
        viewModel = viewModel,
        onClick = { navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        textResourceId = R1.string.movies
    )
}

@Composable
private fun TVShowsTabContent(
    navController: NavController,
    viewModel: BookmarkTVShowViewModel = hiltViewModel()
) {
    TabContent(
        viewModel = viewModel,
        onClick = { navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        textResourceId = R1.string.tv_series
    )
}

@Composable
private fun <T : TMDbItem> TabContent(
    viewModel: BaseViewModel<List<T>>,
    onClick: (TMDbItem) -> Unit,
    @StringRes textResourceId: Int
) {
    viewModel.refresh()
    Content(viewModel = viewModel) {
        if (it.isEmpty()) {
            EmptyView(textResourceId = textResourceId)
        } else {
            TabContent(items = it, onClick = onClick)
        }
    }
}

@Composable
private fun <T : TMDbItem> TabContent(items: List<T>, onClick: (TMDbItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(
            start = TMDb_8_dp,
            end = TMDb_8_dp,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(56.dp)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            TMDb_8_dp,
            Alignment.CenterHorizontally
        ),
        content = {
            items(items.size) { index ->
                TMDbItemContent(
                    items[index],
                    Modifier
                        .height(320.dp)
                        .padding(vertical = TMDb_8_dp),
                    onClick
                )
            }
        }
    )
}

@Composable
private fun EmptyView(@StringRes textResourceId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isEmptyImageVisible()) {
            Image(
                modifier = Modifier.padding(bottom = TMDb_16_dp),
                painter = painterResource(id = R.drawable.ic_empty),
                contentDescription = stringResource(id = R.string.empty_list)
            )
        }
        Text(
            text = stringResource(
                id = R.string.empty_list,
                stringResource(id = textResourceId)
            ),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun isEmptyImageVisible(): Boolean {
    val configuration = LocalConfiguration.current
    val isPortrait = when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> true
        else -> false
    }
    if (isPortrait) {
        return true
    } else {
        val isTablet = booleanResource(id = R.bool.is_tablet)
        if (isTablet) {
            return true
        }
    }
    return false
}

enum class MediaTab(@StringRes val titleResourceId: Int) {
    Movies(titleResourceId = R.string.movie),
    TvShows(titleResourceId = R.string.tv_show)
}