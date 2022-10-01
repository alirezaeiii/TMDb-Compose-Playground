package com.android.sample.tmdb.network

import com.android.sample.tmdb.data.ItemWrapper
import com.android.sample.tmdb.data.Movie
import retrofit2.http.GET

interface MovieService {

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): ItemWrapper<Movie>

    @GET("3/movie/popular")
    suspend fun popularMovies(): ItemWrapper<Movie>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(): ItemWrapper<Movie>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(): ItemWrapper<Movie>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): ItemWrapper<Movie>
}