package com.sample.tmdb.common.model

import com.sample.tmdb.navigation.TMDbNavKey

interface TMDbItem {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterUrl: String?
    val backdropUrl: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int
    val detailNavKey: TMDbNavKey
}
