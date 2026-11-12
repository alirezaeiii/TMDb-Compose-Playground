package com.sample.tmdb.feed.utils

import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

fun Modifier.pagerTransition(pagerState: PagerState, page: Int) = graphicsLayer {
    val pageOffset = pagerState.calculatePageOffset(page)

    lerp(
        start = 0.85f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f),
    ).also { scale ->
        scaleX = scale
        scaleY = scale
    }

    alpha =
        lerp(
            start = 0.5f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f),
        )
}

private fun PagerState.calculatePageOffset(page: Int) = ((currentPage - page) + currentPageOffsetFraction).absoluteValue
