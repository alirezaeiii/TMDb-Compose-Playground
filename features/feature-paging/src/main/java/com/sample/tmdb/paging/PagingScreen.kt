package com.sample.tmdb.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.PagingContent
import com.sample.tmdb.common.ui.component.TMDbContent
import com.sample.tmdb.common.ui.component.TMDbGrid
import com.sample.tmdb.common.utils.pagingItems
import com.sample.tmdb.navigation.TMDbNavKey

@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onNavigate: (TMDbNavKey) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    onClick: (T) -> Unit = viewModel::onItemClick,
) {
    PagingContent(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onNavigateUp = onNavigateUp,
        emptyContent = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                NoDataFoundAnimation(modifier = Modifier.size(200.dp))
            }
        },
    ) { lazyTMDbItems ->
        LazyTMDbItemGrid(lazyTMDbItems, onClick)
    }
}

@Composable
private fun <T : TMDbItem> LazyTMDbItemGrid(lazyTMDbItems: LazyPagingItems<T>, onClick: (T) -> Unit) {
    TMDbGrid {
        pagingItems(lazyTMDbItems) { item ->
            TMDbContent(item, onClick = { onClick(item) })
        }
    }
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
