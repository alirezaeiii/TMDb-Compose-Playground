package com.android.sample.tmdb.data.network

import com.android.sample.tmdb.data.response.ItemWrapper
import com.android.sample.tmdb.data.response.NetworkMovie
import retrofit2.http.GET

interface MovieService {

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/popular")
    suspend fun popularMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): ItemWrapper<NetworkMovie>
}