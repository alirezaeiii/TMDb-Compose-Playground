package com.sample.tmdb.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import androidx.palette.graphics.Palette

val AshGray = Color(0xFFD3D3D3)
val GRAY = Color(0xFF808080)
val DarkGray = Color(0xFF525252)
val Teal200 = Color(0xFF03DAC5)

const val AlphaNearOpaque = 0.65f

@Composable
fun Color.Companion.rateColors(movieRate: Double): List<Color> = remember(movieRate) {
    when {
        movieRate <= 4.5 -> listOf(Color(0xffe32d20), Color(0xff9c180e))
        movieRate < 7 -> listOf(Color(0xffe36922), Color(0xff963d09))
        movieRate < 8.5 -> listOf(Color(0xff87bf32), Color(0xff578216))
        else -> listOf(Color(0xff34c937), Color(0xff0d750f))
    }
}

@Composable
fun GetVibrantColorFromPoster(
    posterUrl: String,
    color: Animatable<Color, AnimationVector4D>
) {
    val context = LocalContext.current
    LaunchedEffect(posterUrl) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(posterUrl)
            .size(128, 128)
            .allowHardware(false)
            .build()

        val bitmap = (loader.execute(request) as? SuccessResult)?.drawable?.toBitmap() ?: return@LaunchedEffect
        val vibrantColor = Palette.from(bitmap).generate().getVibrantColor(color.value.toArgb())
        color.animateTo(Color(vibrantColor), tween(400))
    }
}

val Colors.imageTint: Color
    get() = if (isLight) Color.Gray else Color.DarkGray