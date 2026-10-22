package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.utils.navigationBarPadding

@Composable
fun TMDbGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues =
        PaddingValues(
            start = TMDb_8_dp,
            end = TMDb_8_dp,
            bottom = navigationBarPadding() + TMDb_8_dp,
        ),
    content: LazyGridScope.() -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = contentPadding,
        horizontalArrangement =
            Arrangement.spacedBy(
                TMDb_8_dp,
                Alignment.CenterHorizontally,
            ),
        modifier = modifier,
        content = content,
    )
}
