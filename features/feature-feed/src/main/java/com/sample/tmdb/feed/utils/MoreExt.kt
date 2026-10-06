package com.sample.tmdb.feed.utils

import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.feed.ContentType
import com.sample.tmdb.feed.FeedNavigationEvent
import com.sample.tmdb.navigation.TMDbNavKey

fun FeedNavigationEvent.More.toNavKey(): TMDbNavKey = when (contentType) {
    ContentType.MOVIE -> {
        when (sortType) {
            SortType.TRENDING ->
                TMDbNavKey.TrendingMovies

            SortType.MOST_POPULAR ->
                TMDbNavKey.PopularMovies

            SortType.NOW_PLAYING ->
                TMDbNavKey.NowPlayingMovies

            SortType.UPCOMING ->
                TMDbNavKey.UpcomingMovies

            SortType.DISCOVER ->
                TMDbNavKey.DiscoverMovies

            SortType.HIGHEST_RATED ->
                TMDbNavKey.TopRatedMovies
        }
    }

    ContentType.TV_SHOW -> {
        when (sortType) {
            SortType.TRENDING ->
                TMDbNavKey.TrendingTvShows

            SortType.MOST_POPULAR ->
                TMDbNavKey.PopularTvShows

            SortType.NOW_PLAYING ->
                TMDbNavKey.AiringTodayTvShows

            SortType.UPCOMING ->
                TMDbNavKey.OnTheAirTvShows

            SortType.DISCOVER ->
                TMDbNavKey.DiscoverTvShows

            SortType.HIGHEST_RATED ->
                TMDbNavKey.TopRatedTvShows
        }
    }
}
