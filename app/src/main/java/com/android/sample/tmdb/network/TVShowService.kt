package com.android.sample.tmdb.network

import com.android.sample.tmdb.data.ItemWrapper
import com.android.sample.tmdb.data.TVShow
import retrofit2.http.GET

interface TVShowService {

    @GET("3/trending/tv/day")
    suspend fun trendingTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/popular")
    suspend fun popularTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/airing_today")
    suspend fun airingTodayTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/on_the_air")
    suspend fun onTheAirTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/top_rated")
    suspend fun topRatedTVSeries(): ItemWrapper<TVShow>
}