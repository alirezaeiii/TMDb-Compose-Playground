package com.android.sample.tmdb.ui.paging.search

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.common.TMDbDivider
import com.android.sample.tmdb.ui.paging.PagingScreen

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
    Surface(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.statusBarsPadding()) {
            when {
                query.isEmpty() -> { /*todo */
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
                    .background(MaterialTheme.colors.background)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val iconModifier = Modifier
                        .sizeIn(
                            maxWidth = 32.dp,
                            maxHeight = 32.dp
                        )
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )

                    IconButton(
                        onClick = upPress,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .then(iconModifier)
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.surface
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
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(MaterialTheme.colors.primary, CircleShape)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
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
                if (searchFocused) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = MaterialTheme.colors.surface,
                            contentDescription = "Back"
                        )
                    }
                }
                BasicTextField(
                    value = query,
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.surface),
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                )
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
            tint = MaterialTheme.colors.surface,
            contentDescription = "Search"
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.Search, stringResource(resourceId)),
            color = MaterialTheme.colors.surface
        )
    }
}