package com.sample.tmdb.data.response

import com.sample.tmdb.common.model.People
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PeopleDTO(
    @Json(name = "results")
    val results: List<TrendingPersonDTO>,
)

fun PeopleDTO.asDomainModel(): People = People(results.map(TrendingPersonDTO::asDomainModel))
