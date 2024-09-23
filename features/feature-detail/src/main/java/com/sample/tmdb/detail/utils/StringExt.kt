package com.sample.tmdb.detail.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun String.openInChromeCustomTab(context: Context, color: Color) {
    val schemeParams = CustomTabColorSchemeParams.Builder().setToolbarColor(color.toArgb()).build()
    val customTabsIntent = CustomTabsIntent.Builder().setDefaultColorSchemeParams(schemeParams).build()
    customTabsIntent.launchUrl(context, Uri.parse(this))
}
