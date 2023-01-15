package com.android.sample.tmdb.ui.paging

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.common.TMDbDivider
import com.android.sample.tmdb.ui.common.mirroringBackIcon

@Composable
fun <T : TMDbItem> Search(
    viewModel: BaseSearchPagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    @StringRes resourceId: Int,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState()
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.statusBarsPadding())
            SearchBar(
                query = state.query,
                resourceId = resourceId,
                onQueryChange = { state.query = it },
                searchFocused = state.focused,
                onSearchFocusChange = { state.focused = it },
                onClearQuery = { state.query = TextFieldValue("") },
            )
            TMDbDivider()

            when (state.searchDisplay) {
                SearchDisplay.Results -> {
                    viewModel.showResult(state.query.text)
                    PagingScreen(
                        viewModel = viewModel,
                        onClick = onClick
                    )
                }
                SearchDisplay.NoResults -> { /* todo */
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: TextFieldValue,
    @StringRes resourceId: Int,
    onQueryChange: (TextFieldValue) -> Unit,
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
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty()) {
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
                            imageVector = mirroringBackIcon(),
                            tint = Color.Gray,
                            contentDescription = "Back"
                        )
                    }
                }
                BasicTextField(
                    value = query,
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
            tint = Color.LightGray,
            contentDescription = stringResource(R.string.Search, stringResource(resourceId))
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Search",
            color = Color.LightGray
        )
    }
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused
        )
    }
}

enum class SearchDisplay {
    Results, NoResults
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    val searchDisplay: SearchDisplay
        get() = when {
            query.text.isEmpty() -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}