package com.sample.tmdb.ui

import kotlinx.serialization.Serializable

@Serializable
sealed interface TMDbNavKey : androidx.navigation3.runtime.NavKey {

    @Serializable
    data object Movie : TMDbNavKey

    @Serializable
    data object TvShow : TMDbNavKey

    @Serializable
    data object Bookmark : TMDbNavKey

    @Serializable
    data object Setting : TMDbNavKey

    @Serializable
    data class MovieDetail(val id: Int) : TMDbNavKey

    @Serializable
    data class TvShowDetail(val id: Int) : TMDbNavKey

    @Serializable
    data object TrendingMovies : TMDbNavKey

    @Serializable
    data object PopularMovies : TMDbNavKey

    @Serializable
    data object NowPlayingMovies : TMDbNavKey

    @Serializable
    data object UpcomingMovies : TMDbNavKey

    @Serializable
    data object TopRatedMovies : TMDbNavKey

    @Serializable
    data object DiscoverMovies : TMDbNavKey

    @Serializable
    data class SimilarMovies(val id: Int) : TMDbNavKey

    @Serializable
    data object TrendingTvShows : TMDbNavKey

    @Serializable
    data object PopularTvShows : TMDbNavKey

    @Serializable
    data object AiringTodayTvShows : TMDbNavKey

    @Serializable
    data object OnTheAirTvShows : TMDbNavKey

    @Serializable
    data object TopRatedTvShows : TMDbNavKey

    @Serializable
    data object DiscoverTvShows : TMDbNavKey

    @Serializable
    data class SimilarTvShows(val id: Int) : TMDbNavKey

    @Serializable
    data object SearchMovies : TMDbNavKey

    @Serializable
    data object SearchTvShows : TMDbNavKey

    @Serializable
    data class Cast(val creditsJson: String) : TMDbNavKey

    @Serializable
    data class Crew(val creditsJson: String) : TMDbNavKey

    @Serializable
    data class Person(val id: Int) : TMDbNavKey

    @Serializable
    data class Images(val imagesJson: String, val initialPage: Int) : TMDbNavKey
}
