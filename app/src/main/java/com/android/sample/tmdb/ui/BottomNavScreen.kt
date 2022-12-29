package com.android.sample.tmdb.ui

import com.android.sample.tmdb.R

sealed class BottomNavScreen(val route: String, val title: String, val icon: Int) {
    object MovieNavItem : BottomNavScreen("Movie", "Movie", R.drawable.ic_movie)
    object TVShowNavItem : BottomNavScreen("TVShow","TVShow", R.drawable.ic_tv_series)
}