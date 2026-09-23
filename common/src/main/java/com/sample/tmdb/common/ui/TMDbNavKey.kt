package com.sample.tmdb.common.ui

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
interface TMDbNavKey : NavKey

@Serializable
data class MovieDetail(val id: Int) : TMDbNavKey

@Serializable
data class TvShowDetail(val id: Int) : TMDbNavKey

@Serializable
data object SearchMovies : TMDbNavKey

@Serializable
data object SearchTvShows : TMDbNavKey

@Serializable
data class Person(val id: Int) : TMDbNavKey
