package com.sample.tmdb.paging

import androidx.compose.runtime.Composable
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.ui.PagingContent
import com.sample.tmdb.common.ui.component.TMDbContent
import com.sample.tmdb.common.ui.component.TMDbGrid
import com.sample.tmdb.common.utils.pagingItems
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.navigation.TMDbNavKey

@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    onClick: (T) -> Unit = viewModel::onItemClick,
) {
    PagingContent(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
    ) { lazyTMDbItems ->
        TMDbGrid {
            pagingItems(lazyTMDbItems) { item ->
                TMDbContent(item, onClick = { onClick(item) })
            }
        }
    }
}
