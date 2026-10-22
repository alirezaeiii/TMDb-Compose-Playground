package com.sample.tmdb.common.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.LoadingRow

fun LazyGridScope.fullSpanGridItem(content: @Composable () -> Unit) {
    item(span = { GridItemSpan(maxLineSpan) }) {
        content()
    }
}

fun <T : Any> LazyGridScope.pagingItems(lazyItems: LazyPagingItems<T>, itemContent: @Composable (T) -> Unit) {
    fullSpanGridItem {
        TMDbSpacer()
    }

    items(lazyItems.itemCount) { index ->
        val item = lazyItems[index]
        item?.let {
            itemContent(it)
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
}
