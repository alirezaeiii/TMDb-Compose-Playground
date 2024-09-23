package com.sample.tmdb.data.network

import com.sample.tmdb.data.response.PersonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {
    @GET("3/person/{personId}")
    suspend fun getPerson(@Path("personId") personId: String): PersonDto
}
