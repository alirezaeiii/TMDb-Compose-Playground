package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.ui.Dimens.TMDb_104_dp
import com.sample.tmdb.common.utils.ViewState

@Composable
fun <T, S> TMDbSwipeRefresh(
    viewModel: BaseViewModel<T, S>,
    state: ViewState<T>,
    isRefreshing: Boolean = state.isRefreshing,
    onRefresh: () -> Unit = { viewModel.refresh(true) },
    mainContent: @Composable () -> Unit,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { onRefresh.invoke() },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state,
                trigger,
            )
        },
        modifier = Modifier.fillMaxSize(),
        indicatorPadding = PaddingValues(top = TMDb_104_dp),
    ) {
        mainContent()
    }
}
