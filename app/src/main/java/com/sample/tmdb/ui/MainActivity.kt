package com.sample.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.core.view.WindowCompat

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque
import com.sample.tmdb.common.ui.theme.TmdbPagingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TmdbPagingComposeTheme {
                val sysUiController = rememberSystemUiController()
                sysUiController.setSystemBarsColor(
                    color = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque)
                )
                TMDbApp()
            }
        }
    }
}