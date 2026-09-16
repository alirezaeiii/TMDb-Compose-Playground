package com.sample.tmdb.ui

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import com.sample.tmdb.common.model.ThemeMode
import com.sample.tmdb.common.ui.ThemeViewModel
import com.sample.tmdb.common.ui.theme.AlphaNavigationBar
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque
import com.sample.tmdb.common.ui.theme.TmdbPagingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        enableEdgeToEdge()
        setContent {
            val themeMode by themeViewModel.themeMode.collectAsState()
            val darkTheme =
                when (themeMode) {
                    ThemeMode.LIGHT -> false
                    ThemeMode.DARK -> true
                    ThemeMode.SYSTEM -> isSystemInDarkTheme()
                }
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides this) {
                TmdbPagingComposeTheme(darkTheme = darkTheme) {
                    ChangeSystemBarsTheme(!darkTheme)
                    TMDbApp()
                }
            }
        }
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val statusBarColor = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque).toArgb()
        val navigationBarColor = MaterialTheme.colors.background.copy(alpha = AlphaNavigationBar).toArgb()
        LaunchedEffect(lightTheme) {
            if (lightTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(statusBarColor, statusBarColor),
                    navigationBarStyle = SystemBarStyle.light(navigationBarColor, navigationBarColor),
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(statusBarColor),
                    navigationBarStyle = SystemBarStyle.dark(navigationBarColor),
                )
            }
        }
    }
}
