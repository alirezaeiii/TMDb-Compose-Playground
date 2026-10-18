package com.sample.tmdb.person.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.HandleUiEvents
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.LoadingRow
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.ui.component.PersonGrid
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
    PersonGrid(
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
