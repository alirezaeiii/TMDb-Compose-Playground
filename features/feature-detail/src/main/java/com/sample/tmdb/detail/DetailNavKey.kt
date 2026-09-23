package com.sample.tmdb.detail

import com.sample.tmdb.common.ui.TMDbNavKey
import kotlinx.serialization.Serializable

@Serializable
data class SimilarMovies(val id: Int) : TMDbNavKey

@Serializable
data class SimilarTvShows(val id: Int) : TMDbNavKey

@Serializable
data class Cast(val creditsJson: String) : TMDbNavKey

@Serializable
data class Crew(val creditsJson: String) : TMDbNavKey

@Serializable
data class Images(val imagesJson: String, val initialPage: Int) : TMDbNavKey
