package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sample.tmdb.common.ui.Dimens.TMDb_56_dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TMDbSwipeRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    indicatorTopPadding: Dp = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + TMDb_56_dp,
    mainContent: @Composable () -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onRefresh.invoke() },
        state = pullToRefreshState,
        modifier = Modifier.fillMaxSize(),
        indicator = {
            PullToRefreshDefaults.Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = indicatorTopPadding),
                isRefreshing = isRefreshing,
                state = pullToRefreshState,
            )
        },
    ) {
        mainContent()
    }
}
