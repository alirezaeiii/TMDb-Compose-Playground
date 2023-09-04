package com.sample.tmdb.ui.bookmark

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.ui.BaseViewModel
import com.sample.tmdb.ui.Content
import com.sample.tmdb.ui.common.*
import com.sample.tmdb.ui.theme.AlphaNearOpaque
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
    TabContent(viewModel = viewModel, onClick = onClickMovie, textResourceId = R.string.movies)
}

@Composable
private fun TVShowsTabContent(
    onClickTVShow: (TMDbItem) -> Unit,
    viewModel: BookmarkTVShowViewModel = hiltViewModel()
) {
    TabContent(viewModel = viewModel, onClick = onClickTVShow, textResourceId = R.string.tv_series)
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

@Composable
private fun EmptyView(@StringRes textResourceId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.PaddingLarge, bottom = Dimens.PaddingExtraLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(bottom = Dimens.PaddingLarge),
            painter = painterResource(id = R.drawable.ic_empty),
            contentDescription = stringResource(id = R.string.empty_list)
        )
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

enum class MediaTab(@StringRes val titleResourceId: Int) {
    Movies(titleResourceId = R.string.movie),
    TvShows(titleResourceId = R.string.tv_show)
}