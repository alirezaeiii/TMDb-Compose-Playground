package com.sample.tmdb.person.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.ui.Dimens.TMDb_120_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.HandleUiEvents
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.LoadingRow
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.TMDbSpacer
import com.sample.tmdb.common.utils.fullSpanGridItem
import com.sample.tmdb.common.utils.getPagingErrorMsg
import com.sample.tmdb.common.utils.navigationBarPadding
import com.sample.tmdb.navigation.TMDbNavKey

@Composable
fun PagingPersonScreen(
    viewModel: PersonPagingViewModel,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    onSearchClick: () -> Unit = viewModel::onSearchClick,
    onClick: (TrendingPerson) -> Unit = viewModel::onItemClick,
) {
    Box {
        PagingPersonScreen(viewModel, onNavigate, onNavigateUp, onClick)
        DestinationBar(
            title = stringResource(commonR.string.trending_people),
            onSearchClicked = onSearchClick,
            upPress = viewModel::onNavigateUp,
        )
    }
}

@Composable
fun PagingPersonScreen(
    viewModel: BasePagingViewModel<TrendingPerson>,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    onClick: (TrendingPerson) -> Unit = viewModel::onItemClick,
) {
    HandleUiEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
    )

    val lazyItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    when (lazyItems.loadState.refresh) {
        is LoadState.Loading -> {
            TMDbProgressBar()
        }

        is LoadState.Error -> {
            ErrorScreen(
                message = getPagingErrorMsg(lazyItems.loadState.refresh),
                modifier = Modifier.fillMaxSize(),
                refresh = { lazyItems.retry() },
            )
        }

        else -> {
            LazyItemGrid(lazyItems, onClick)
        }
    }
}

@Composable
private fun LazyItemGrid(lazyItems: LazyPagingItems<TrendingPerson>, onClick: (TrendingPerson) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(TMDb_120_dp),
        contentPadding =
            PaddingValues(
                start = TMDb_6_dp,
                end = TMDb_6_dp,
                top = TMDb_6_dp,
                bottom = navigationBarPadding().plus(TMDb_8_dp),
            ),
        horizontalArrangement =
            Arrangement.spacedBy(
                TMDb_8_dp,
                Alignment.CenterHorizontally,
            ),
        content = {
            fullSpanGridItem {
                TMDbSpacer()
            }

            items(lazyItems.itemCount) { index ->
                val item = lazyItems[index]
                item?.let {
                    PersonCard(it, onClick)
                }
            }

            if (lazyItems.loadState.append is LoadState.Loading) {
                fullSpanGridItem {
                    LoadingRow(modifier = Modifier.padding(vertical = TMDb_8_dp))
                }
            }

            if (lazyItems.loadState.append is LoadState.Error) {
                fullSpanGridItem {
                    ErrorScreen(
                        message = getPagingErrorMsg(lazyItems.loadState.append),
                        modifier = Modifier.padding(vertical = TMDb_8_dp),
                        refresh = { lazyItems.retry() },
                    )
                }
            }
        },
    )
}
