package com.sample.tmdb.paging.search

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_32_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.component.AnimatedSearch
import com.sample.tmdb.common.ui.component.InfinitelyFlowingCircles
import com.sample.tmdb.common.ui.component.SearchBar
import com.sample.tmdb.common.ui.component.TMDbDivider
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.UiEvent
import com.sample.tmdb.paging.PagingScreen
import kotlinx.coroutines.flow.collectLatest

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
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigation -> onNavigate(event.route)
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }

    var query by rememberSaveable { mutableStateOf("") }
    var focused by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize()) {
        if (query.isEmpty()) {
            InfinitelyFlowingCircles()
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = 28.dp, end = 28.dp),
                contentAlignment = Alignment.Center,
            ) {
                AnimatedSearch()
            }
        } else {
            viewModel.showResult(query)
            PagingScreen(
                viewModel = viewModel,
            )
        }
        Column(
            modifier =
                Modifier
                    .statusBarsPadding()
                    .background(MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque)),
        ) {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(TMDb_8_dp),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        TMDb_16_dp,
                        Alignment.CenterHorizontally,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val iconModifier =
                    Modifier
                        .sizeIn(
                            maxWidth = TMDb_32_dp,
                            maxHeight = TMDb_32_dp,
                        )
                        .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = CircleShape,
                        )
                IconButton(
                    onClick = viewModel::onNavigateUp,
                    modifier =
                        Modifier
                            .padding(start = TMDb_12_dp)
                            .then(iconModifier),
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = commonR.string.back),
                        tint = MaterialTheme.colors.primary,
                    )
                }
                SearchBar(
                    query = query,
                    resourceId = resourceId,
                    onQueryChange = { query = it },
                    searchFocused = focused,
                    onSearchFocusChange = { focused = it },
                    onClearQuery = { query = "" },
                )
            }
            TMDbDivider()
        }
    }
}
