package com.android.sample.tmdb.data.network

import com.android.sample.tmdb.data.response.NetworkCreditWrapper
import com.android.sample.tmdb.data.response.TMDbWrapper
import com.android.sample.tmdb.data.response.NetworkTVShow
import retrofit2.http.GET
import retrofit2.http.Path

interface TVShowService {

    @GET("3/trending/tv/day")
    suspend fun trendingTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/popular")
    suspend fun popularTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/airing_today")
    suspend fun airingTodayTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/on_the_air")
    suspend fun onTheAirTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/top_rated")
    suspend fun topRatedTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/{tvId}/credits")
    suspend fun tvCredit(@Path("tvId") tvId: Int): NetworkCreditWrapper
}