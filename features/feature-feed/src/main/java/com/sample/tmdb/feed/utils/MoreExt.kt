package com.sample.tmdb.feed.utils

import com.sample.tmdb.common.ui.TMDbNavKey
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.feed.AiringTodayTvShows
import com.sample.tmdb.feed.ContentType
import com.sample.tmdb.feed.DiscoverMovies
import com.sample.tmdb.feed.DiscoverTvShows
import com.sample.tmdb.feed.FeedNavigationEvent
import com.sample.tmdb.feed.NowPlayingMovies
import com.sample.tmdb.feed.OnTheAirTvShows
import com.sample.tmdb.feed.PopularMovies
import com.sample.tmdb.feed.PopularTvShows
import com.sample.tmdb.feed.TopRatedMovies
import com.sample.tmdb.feed.TopRatedTvShows
import com.sample.tmdb.feed.TrendingMovies
import com.sample.tmdb.feed.TrendingTvShows
import com.sample.tmdb.feed.UpcomingMovies

fun FeedNavigationEvent.More.toNavKey(): TMDbNavKey = when (contentType) {
    ContentType.MOVIE -> {
        when (sortType) {
            SortType.TRENDING ->
                TrendingMovies

            SortType.MOST_POPULAR ->
                PopularMovies

            SortType.NOW_PLAYING ->
                NowPlayingMovies

            SortType.UPCOMING ->
                UpcomingMovies

            SortType.DISCOVER ->
                DiscoverMovies

            SortType.HIGHEST_RATED ->
                TopRatedMovies
        }
    }

    ContentType.TV_SHOW -> {
        when (sortType) {
            SortType.TRENDING ->
                TrendingTvShows

            SortType.MOST_POPULAR ->
                PopularTvShows

            SortType.NOW_PLAYING ->
                AiringTodayTvShows

            SortType.UPCOMING ->
                OnTheAirTvShows

            SortType.DISCOVER ->
                DiscoverTvShows

            SortType.HIGHEST_RATED ->
                TopRatedTvShows
        }
    }
}
