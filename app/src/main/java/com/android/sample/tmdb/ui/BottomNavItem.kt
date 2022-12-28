package com.android.sample.tmdb.ui

import com.android.sample.tmdb.R

sealed class BottomNavItem(val route: String, val title: String, val icon: Int) {
    object Movie : BottomNavItem("Movie", "Movie", R.drawable.ic_movie)
    object TVShow : BottomNavItem("TVShow","TVShow", R.drawable.ic_tv_series)
}