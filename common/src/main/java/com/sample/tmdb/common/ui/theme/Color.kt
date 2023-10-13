package com.sample.tmdb.common.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

val AshGray = Color(0xFFD3D3D3)
val GRAY = Color(0xFF808080)
val DarkGray = Color(0xFF525252)
val Teal200 = Color(0xFF03DAC5)
val Tornado = listOf(Color(0xff7057f5), Color(0xff86f7fa))
val Neutral8 = Color(0xff121212)

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

val Colors.imageTint: Color
    get() = if (isLight) Color.Gray else Color.DarkGray