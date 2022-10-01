package com.android.sample.tmdb.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.android.sample.tmdb.R
import com.android.sample.tmdb.data.FeedWrapper
import com.android.sample.tmdb.data.TMDbItem
import com.android.sample.tmdb.ui.common.ErrorScreen
import com.android.sample.tmdb.ui.common.LoopReverseLottieLoader
import com.android.sample.tmdb.utils.Resource

@Composable
fun <T : TMDbItem> Content(
    viewModel: BaseFeedViewModel<T>,
    SuccessScreen: @Composable (t: List<FeedWrapper<T>>) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> LoopReverseLottieLoader(lottieFile = R.raw.loader)
        is Resource.Success -> SuccessScreen(resource.data)
        is Resource.Error -> ErrorScreen(message = resource.message, refresh = viewModel::refresh)
    }
}