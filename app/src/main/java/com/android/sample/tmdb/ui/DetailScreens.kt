package com.android.sample.tmdb.ui

sealed class DetailScreens(val title: String) {
    object MovieDetails : DetailScreens("movie_detail_screen/{$TMDb_ITEM}")
    object TVShowDetails : DetailScreens("tv_show_detail_screen/{$TMDb_ITEM}")
    
    companion object {
        const val TMDb_ITEM = "TMDbItem"
    }
}