package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.ui.Dimens.TMDb_104_dp
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.navigation.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T, S, E : UiEvent> TMDbSwipeRefresh(
    viewModel: BaseViewModel<T, S, E>,
    state: ViewState<T>,
    isRefreshing: Boolean = state.isRefreshing,
    onRefresh: () -> Unit = { viewModel.refresh(true) },
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
                    .padding(top = TMDb_104_dp),
                isRefreshing = isRefreshing,
                state = pullToRefreshState,
            )
        },
    ) {
        mainContent()
    }
}
