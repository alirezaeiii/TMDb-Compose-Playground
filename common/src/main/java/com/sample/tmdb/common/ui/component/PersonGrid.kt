package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.tmdb.common.ui.Dimens.TMDb_120_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp

@Composable
fun PersonGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues =
        PaddingValues(
            start = TMDb_6_dp,
            end = TMDb_6_dp,
            top = TMDb_6_dp,
        ),
    content: LazyGridScope.() -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(TMDb_120_dp),
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
