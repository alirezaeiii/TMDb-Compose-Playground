package com.sample.tmdb.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface TMDbNavKey : NavKey {

    @Serializable
    data object MovieNav : TMDbNavKey

    @Serializable
    data object TvShowNav : TMDbNavKey

    @Serializable
    data object Bookmark : TMDbNavKey

    @Serializable
    data object Setting : TMDbNavKey

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

    @Serializable
    data class PagingMovies(val sortType: SortType, val id: Int? = null) : TMDbNavKey

    @Serializable
    data class PagingTvShows(val sortType: SortType, val id: Int? = null) : TMDbNavKey

    @Serializable
    data class Cast(val creditsJson: String) : TMDbNavKey

    @Serializable
    data class Crew(val creditsJson: String) : TMDbNavKey

    @Serializable
    data class Images(val imagesJson: String, val initialPage: Int) : TMDbNavKey
}
