package com.sample.tmdb.common.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreenContent(
    @StringRes resourceId: Int,
    onNavigateUp: () -> Unit,
    onShowResult: (String) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
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
            onShowResult(query)
            content()
        }
        SearchTopBar(
            query = query,
            resourceId = resourceId,
            onQueryChange = { query = it },
            searchFocused = focused,
            onSearchFocusChange = { focused = it },
            onClearQuery = { query = "" },
            onNavigateUp = onNavigateUp,
        )
    }
}
