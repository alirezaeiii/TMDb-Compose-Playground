package com.sample.tmdb.common.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TMDbProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalDottedProgressBar()
    }
}

@Composable
fun HorizontalDottedProgressBar() {
    val color = MaterialTheme.colors.primary

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val state =
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 6f,
            animationSpec =
            infiniteRepeatable(
                animation =
                tween(
                    durationMillis = 700,
                    easing = LinearEasing,
                ),
            ),
            label = "",
        )

    DrawCanvas(state = state.value, radius = 15.dp, color = color)
}

@Composable
fun DrawCanvas(state: Float, radius: Dp, color: Color) {
    Canvas(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(55.dp),
    ) {
        val radiusValue = radius.value
        val padding = (radiusValue + (radiusValue * 0.5f))

        for (i in 1..5) {
            if (i - 1 == state.toInt()) {
                drawCircle(
                    radius = radiusValue * 2,
                    brush = SolidColor(color),
                    center = Offset(
                        x = this.center.x + radiusValue * 2 * (i - 3) + padding * (i - 3),
                        y = this.center.y,
                    ),
                )
            } else {
                drawCircle(
                    radius = radiusValue,
                    brush = SolidColor(color),
                    center = Offset(
                        x = this.center.x + radiusValue * 2 * (i - 3) + padding * (i - 3),
                        y = this.center.y,
                    ),
                )
            }
        }
    }
}
