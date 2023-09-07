package com.sample.tmdb.ui.paging.search

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.ui.common.Dimens
import com.sample.tmdb.ui.common.TMDbDivider
import com.sample.tmdb.ui.paging.PagingScreen
import com.sample.tmdb.ui.theme.AlphaNearOpaque

@Composable
fun SearchMoviesScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    viewModel: SearchMoviesViewModel = hiltViewModel()
) {
    Search(
        viewModel = viewModel,
        onClick = onClick,
        upPress = upPress,
        resourceId = R.string.movies
    )
}

@Composable
fun SearchTVSeriesScreen(
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    viewModel: SearchTvSeriesViewModel = hiltViewModel()
) {
    Search(
        viewModel = viewModel,
        onClick = onClick,
        upPress = upPress,
        resourceId = R.string.tv_series
    )
}

@Composable
fun <T : TMDbItem> Search(
    viewModel: BaseSearchPagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    @StringRes resourceId: Int,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    var focused by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize()) {
        when {
            query.isEmpty() -> {
                InfinitelyFlowingCircles()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 28.dp, end = 28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedSearch()
                }
            }
            else -> {
                viewModel.showResult(query)
                PagingScreen(
                    viewModel = viewModel,
                    onClick = onClick
                )
            }
        }
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .background(MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque))
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(Dimens.PaddingMedium),
                horizontalArrangement = Arrangement.spacedBy(
                    Dimens.PaddingLarge,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val iconModifier = Modifier
                    .sizeIn(
                        maxWidth = 32.dp,
                        maxHeight = 32.dp
                    )
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = CircleShape
                    )
                IconButton(
                    onClick = upPress,
                    modifier = Modifier
                        .padding(start = Dimens.PaddingNormal)
                        .then(iconModifier)
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        tint = MaterialTheme.colors.primary
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

@Composable
private fun SearchBar(
    query: String,
    @StringRes resourceId: Int,
    onQueryChange: (String) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        contentColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
            .background(MaterialTheme.colors.background, CircleShape)
            .padding(horizontal = 24.dp, vertical = Dimens.PaddingMedium)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            if (query.isEmpty()) {
                SearchHint(resourceId)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = query,
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onBackground),
                    cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                )
                if (searchFocused && query.isNotEmpty()) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = MaterialTheme.colors.primary,
                            contentDescription = stringResource(id = R.string.clear)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchHint(@StringRes resourceId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = MaterialTheme.colors.primary,
            contentDescription = stringResource(id = R.string.search_desc)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.search, stringResource(resourceId)),
            color = MaterialTheme.colors.primary
        )
    }
}