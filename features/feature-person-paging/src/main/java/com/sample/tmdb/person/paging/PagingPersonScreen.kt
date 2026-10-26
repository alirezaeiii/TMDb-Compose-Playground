package com.sample.tmdb.person.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.ui.PagingContent
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.ui.component.PersonGrid
import com.sample.tmdb.common.utils.pagingItems
import com.sample.tmdb.domain.model.TrendingPerson
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
    PagingContent(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
    ) { lazyItems ->
        PersonGrid {
            pagingItems(lazyItems) { item ->
                PersonCard(
                    person = item,
                    onPersonClicked = { onClick(item) },
                )
            }
        }
    }
}
