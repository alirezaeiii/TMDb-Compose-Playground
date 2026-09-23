package com.sample.tmdb.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.common.ui.component.ErrorScreen
import com.sample.tmdb.common.ui.component.LoadingRow
import com.sample.tmdb.common.ui.component.TMDbContent
import com.sample.tmdb.common.ui.component.TMDbProgressBar
import com.sample.tmdb.common.utils.TMDbSpacer
import com.sample.tmdb.common.utils.UiEvent
import com.sample.tmdb.common.utils.fullSpanGridItem
import com.sample.tmdb.common.utils.navigationBarPadding
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    onClick: (TMDbItem) -> Unit = viewModel::onItemClick,
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigation -> onNavigate(event.route)
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> {}
            }
        }
    }

    val lazyTMDbItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    when (lazyTMDbItems.loadState.refresh) {
        is LoadState.Loading -> {
            TMDbProgressBar()
        }

        is LoadState.Error -> {
            ErrorScreen(
                message = getErrorMsg(lazyTMDbItems.loadState.refresh),
                modifier = Modifier.fillMaxSize(),
                refresh = { lazyTMDbItems.retry() },
            )
        }

        else -> {
            if (lazyTMDbItems.itemCount == 0) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    NoDataFoundAnimation(modifier = Modifier.size(200.dp))
                }
            } else {
                LazyTMDbItemGrid(lazyTMDbItems, onClick)
            }
        }
    }
}

@Composable
private fun <T : TMDbItem> LazyTMDbItemGrid(lazyTMDbItems: LazyPagingItems<T>, onClick: (TMDbItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding =
        PaddingValues(
            start = TMDb_8_dp,
            end = TMDb_8_dp,
            bottom = navigationBarPadding().plus(TMDb_8_dp),
        ),
        horizontalArrangement =
        Arrangement.spacedBy(
            TMDb_8_dp,
            Alignment.CenterHorizontally,
        ),
        content = {
            fullSpanGridItem {
                TMDbSpacer()
            }

            items(lazyTMDbItems.itemCount) { index ->
                val tmdbItem = lazyTMDbItems[index]
                tmdbItem?.let {
                    TMDbContent(it, onClick)
                }
            }

            if (lazyTMDbItems.loadState.append is LoadState.Loading) {
                fullSpanGridItem {
                    LoadingRow(modifier = Modifier.padding(vertical = TMDb_8_dp))
                }
            }

            if (lazyTMDbItems.loadState.append is LoadState.Error) {
                fullSpanGridItem {
                    ErrorScreen(
                        message = getErrorMsg(lazyTMDbItems.loadState.append),
                        modifier = Modifier.padding(vertical = TMDb_8_dp),
                        refresh = { lazyTMDbItems.retry() },
                    )
                }
            }
        },
    )
}

@Composable
private fun NoDataFoundAnimation(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.no_data_found,
        ),
    )
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )
    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier,
    )
}

@Composable
private fun getErrorMsg(loadState: LoadState) =
    (loadState as LoadState.Error).error.message ?: stringResource(commonR.string.failed_loading_msg)
