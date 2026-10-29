package com.sample.tmdb.data.network

import com.sample.tmdb.data.response.PeopleDTO
import com.sample.tmdb.data.response.PersonDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {
    @GET("3/person/{personId}")
    suspend fun getPerson(@Path("personId") personId: Int): PersonDTO

    @GET("3/trending/person/week")
    suspend fun getTrendingPeople(@Query("page") page: Int = 1): PeopleDTO

    @GET("3/search/person")
    suspend fun getTrendingPeople(@Query("query") query: String, @Query("page") page: Int): PeopleDTO
}
