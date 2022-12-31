package com.android.sample.tmdb.ui

import com.android.sample.tmdb.R

enum class HomeSections(val route: String, val title: String, val icon: Int) {
    MOVIE_SECTION("Movie", "Movie", R.drawable.ic_movie),
    TV_SHOW_SECTION("TVShow","TVShow", R.drawable.ic_tv_series)
}