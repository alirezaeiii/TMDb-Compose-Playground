package com.sample.tmdb.navigation.model

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.TMDbNavKey

fun TMDbItem.toDetailNavKey(): TMDbNavKey = when (this) {
    is Movie -> TMDbNavKey.MovieDetail(id)
    is TVShow -> TMDbNavKey.TvShowDetail(id)
    else -> error("Unsupported TMDbItem: ${this::class}")
}
