package com.android.sample.tmdb.ui

import com.android.sample.tmdb.R

sealed class BottomNavItem(var title: String, var icon: Int) {
    object Movie : BottomNavItem("Movie", R.drawable.ic_movie)
    object TVShow : BottomNavItem("TVShow", R.drawable.ic_tv_series)
}