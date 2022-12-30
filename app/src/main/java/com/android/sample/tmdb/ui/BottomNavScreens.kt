package com.android.sample.tmdb.ui

import com.android.sample.tmdb.R

sealed class BottomNavScreens(val route: String, val title: String, val icon: Int) {
    object MovieNavItem : BottomNavScreens("Movie", "Movie", R.drawable.ic_movie)
    object TVShowNavItem : BottomNavScreens("TVShow","TVShow", R.drawable.ic_tv_series)
}