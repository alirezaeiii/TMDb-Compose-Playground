package com.sample.tmdb.data.response

import com.squareup.moshi.Json

class TMDbWrapper<T : NetworkTMDbItem>(
    @Json(name = "results")
    val items: List<T>
)

class NetworkCreditWrapper(
    val cast: List<NetworkCast>,
    val crew: List<NetworkCrew>
)