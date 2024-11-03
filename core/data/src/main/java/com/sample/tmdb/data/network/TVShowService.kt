package com.sample.tmdb.data.network

import com.sample.tmdb.data.response.ImagesResponse
import com.sample.tmdb.data.response.NetworkCreditWrapper
import com.sample.tmdb.data.response.NetworkTVShow
import com.sample.tmdb.data.response.TMDbWrapper
import com.sample.tmdb.data.response.TvDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("3/discover/tv")
    suspend fun discoverTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/trending/tv/day")
    suspend fun trendingTVSeries(@Query("page") page: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/popular")
    suspend fun popularTVSeries(@Query("page") page: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/airing_today")
    suspend fun airingTodayTVSeries(@Query("page") page: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/on_the_air")
    suspend fun onTheAirTVSeries(@Query("page") page: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/top_rated")
    suspend fun topRatedTVSeries(@Query("page") page: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/discover/tv")
    suspend fun discoverTVSeries(@Query("page") page: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/{tvId}/credits")
    suspend fun tvCredit(@Path("tvId") tvId: Int): NetworkCreditWrapper

    @GET("3/tv/{tvId}")
    suspend fun fetchTvDetail(@Path("tvId") tvId: Int): TvDetailResponse

    @GET("3/search/tv")
    suspend fun searchTVSeries(@Query("page") page: Int, @Query("query") query: String): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/{tvId}/images")
    suspend fun fetchImages(@Path("tvId") tvId: Int): ImagesResponse

    @GET("3/tv/{tvId}/similar")
    suspend fun fetchSimilarTVSeries(@Path("tvId") tvId: Int): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/{TvId}/similar")
    suspend fun fetchSimilarTVSeries(@Path("TvId") tvId: Int, @Query("page") page: Int): TMDbWrapper<NetworkTVShow>
}
