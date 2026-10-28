package com.sample.tmdb.data.network

import com.sample.tmdb.data.response.ImagesResponse
import com.sample.tmdb.data.response.MovieDetailResponse
import com.sample.tmdb.data.response.MovieResponse
import com.sample.tmdb.data.response.NetworkCreditWrapper
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/popular")
    suspend fun popularMovies(): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): NetworkTMDbWrapper<MovieResponse>

    @GET("3/discover/movie")
    suspend fun discoverMovies(): NetworkTMDbWrapper<MovieResponse>

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(@Query("page") page: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/popular")
    suspend fun popularMovies(@Query("page") page: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(@Query("page") page: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(@Query("page") page: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(@Query("page") page: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/discover/movie")
    suspend fun discoverMovies(@Query("page") page: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/discover/movie")
    suspend fun providerMovies(
        @Query("with_watch_providers") id: Int,
        @Query("page") page: Int = 1,
        @Query("watch_region") region: String = "US",
    ): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/{movieId}/credits")
    suspend fun movieCredit(@Path("movieId") movieId: Int): NetworkCreditWrapper

    @GET("3/movie/{movieId}")
    suspend fun fetchMovieDetail(@Path("movieId") movieId: Int): MovieDetailResponse

    @GET("3/search/movie")
    suspend fun searchMovies(@Query("page") page: Int, @Query("query") query: String): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/{movieId}/images")
    suspend fun fetchImages(@Path("movieId") movieId: Int): ImagesResponse

    @GET("3/movie/{movieId}/similar")
    suspend fun fetchSimilarMovies(@Path("movieId") movieId: Int): NetworkTMDbWrapper<MovieResponse>

    @GET("3/movie/{movieId}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int,
    ): NetworkTMDbWrapper<MovieResponse>
}
