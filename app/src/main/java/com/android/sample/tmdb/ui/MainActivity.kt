package com.android.sample.tmdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.core.view.WindowCompat
import com.android.sample.tmdb.ui.theme.AlphaNearOpaque
import com.android.sample.tmdb.ui.theme.TmdbPagingComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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