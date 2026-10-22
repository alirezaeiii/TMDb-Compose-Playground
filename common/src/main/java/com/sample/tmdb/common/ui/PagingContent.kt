package com.sample.tmdb.common.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.getPagingErrorMsg
import com.sample.tmdb.navigation.TMDbNavKey

@Composable
fun <T : Any> PagingContent(
    viewModel: BasePagingViewModel<T>,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    emptyContent: @Composable () -> Unit = {},
    content: @Composable (LazyPagingItems<T>) -> Unit,
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
            if (lazyItems.itemCount == 0) {
                emptyContent()
            } else {
                content(lazyItems)
            }
        }
    }
}
