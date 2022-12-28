package com.android.sample.tmdb.data.network

import com.android.sample.tmdb.data.response.ItemWrapper
import com.android.sample.tmdb.data.response.NetworkTVShow
import retrofit2.http.GET

interface TVShowService {

    @GET("3/trending/tv/day")
    suspend fun trendingTVSeries(): ItemWrapper<NetworkTVShow>

    @GET("3/tv/popular")
    suspend fun popularTVSeries(): ItemWrapper<NetworkTVShow>

    @GET("3/tv/airing_today")
    suspend fun airingTodayTVSeries(): ItemWrapper<NetworkTVShow>

    @GET("3/tv/on_the_air")
    suspend fun onTheAirTVSeries(): ItemWrapper<NetworkTVShow>

    @GET("3/tv/top_rated")
    suspend fun topRatedTVSeries(): ItemWrapper<NetworkTVShow>
}