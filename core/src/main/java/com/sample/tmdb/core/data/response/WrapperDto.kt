package com.sample.tmdb.core.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TMDbWrapper<T : NetworkTMDbItem>(
    @Json(name = "results")
    val items: List<T>
)

@JsonClass(generateAdapter = true)
data class NetworkCreditWrapper(
    @Json(name = "cast")
    val cast: List<NetworkCast>,
    @Json(name = "crew")
    val crew: List<NetworkCrew>
)