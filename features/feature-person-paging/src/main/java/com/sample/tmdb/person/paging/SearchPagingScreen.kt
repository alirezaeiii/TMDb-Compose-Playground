package com.sample.tmdb.person.paging

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sample.tmdb.common.ui.HandleUiEvents
import com.sample.tmdb.common.ui.component.SearchScreenContent
import com.sample.tmdb.navigation.TMDbNavKey

@Composable
fun SearchPagingScreen(
    viewModel: PagingSearchPersonViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HandleUiEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
    )

    SearchScreenContent(
        resourceId = R.string.person,
        onNavigateUp = viewModel::onNavigateUp,
        onShowResult = viewModel::showResult,
        modifier = modifier,
    ) {
        PagingPersonScreen(
            viewModel = viewModel,
        )
    }
}
