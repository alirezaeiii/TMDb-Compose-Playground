package com.android.sample.tmdb.data.response

import com.squareup.moshi.Json

class ItemWrapper<T : NetworkTMDbItem>(
    @Json(name = "results")
    val items: List<T>
)