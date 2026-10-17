package com.sample.tmdb.person.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.sample.tmdb.common.ui.component.SearchScreenContent
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchPagingScreen(
    viewModel: PagingSearchPersonViewModel,
    onNavigate: (TMDbNavKey) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigation -> onNavigate(event.route)
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }

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
