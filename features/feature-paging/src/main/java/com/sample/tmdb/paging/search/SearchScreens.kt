package com.sample.tmdb.paging.search

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.HandleUiEvents
import com.sample.tmdb.common.ui.component.SearchScreenContent
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.PagingScreen

@Composable
fun SearchMoviesScreen(viewModel: SearchMoviesViewModel, onNavigate: (TMDbNavKey) -> Unit, onNavigateUp: () -> Unit) {
    Search(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        resourceId = commonR.string.movies,
    )
}

@Composable
fun SearchTVSeriesScreen(
    viewModel: SearchTVSeriesViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
) {
    Search(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        resourceId = commonR.string.tv_series,
    )
}

@Composable
fun <T : TMDbItem> Search(
    viewModel: BaseSearchTMDbPagingViewModel<T>,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
    @StringRes resourceId: Int,
    modifier: Modifier = Modifier,
) {
    HandleUiEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
    )

    SearchScreenContent(
        resourceId = resourceId,
        onNavigateUp = viewModel::onNavigateUp,
        onShowResult = viewModel::showResult,
        modifier = modifier,
    ) {
        PagingScreen(
            viewModel = viewModel,
        )
    }
}
