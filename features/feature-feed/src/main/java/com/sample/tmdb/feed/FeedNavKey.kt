package com.sample.tmdb.feed

import com.sample.tmdb.common.ui.TMDbNavKey
import kotlinx.serialization.Serializable

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
