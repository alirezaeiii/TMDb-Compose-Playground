package com.sample.tmdb.ui.bookmark

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.ui.Content
import com.sample.tmdb.ui.common.*
import com.sample.tmdb.utils.toDp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkScreen(
    onClickMovie: (TMDbItem) -> Unit,
    onClickTVShow: (TMDbItem) -> Unit,
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
                MediaTab.Movies.ordinal -> MoviesTabContent(onClickMovie)
                MediaTab.TvShows.ordinal -> TVShowsTabContent(onClickTVShow)
            }
        }
    }
}

@Composable
private fun MoviesTabContent(
    onClickMovie: (TMDbItem) -> Unit,
    viewModel: BookmarkMovieViewModel = hiltViewModel()
) {
    viewModel.refresh()
    Content(viewModel = viewModel) {
        TabContent(items = it, onClick = onClickMovie)
    }

}

@Composable
private fun TVShowsTabContent(
    onClickTVShow: (TMDbItem) -> Unit,
    viewModel: BookmarkTVShowViewModel = hiltViewModel()
) {
    viewModel.refresh()
    Content(viewModel = viewModel) {
        TabContent(items = it, onClick = onClickTVShow)
    }
}

@Composable
private fun <T : TMDbItem> TabContent(items: List<T>, onClick: (TMDbItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(
            start = Dimens.PaddingMedium,
            end = Dimens.PaddingMedium,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(
                    Dimens.PaddingMedium
                )
        ),
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.PaddingMedium,
            Alignment.CenterHorizontally
        ),
        content = {

            items(items.size) { index ->
                TMDbItemContent(
                    items[index],
                    Modifier
                        .height(320.dp)
                        .padding(vertical = Dimens.PaddingMedium),
                    onClick
                )
            }
        }
    )
}

enum class MediaTab(@StringRes val titleResourceId: Int) {
    Movies(titleResourceId = R.string.movie),
    TvShows(titleResourceId = R.string.tv_show)
}