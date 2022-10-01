package com.android.sample.tmdb.data

import com.android.sample.tmdb.utils.SortType
import com.squareup.moshi.Json

class ItemWrapper<T : TMDbItem>(
    @Json(name = "results")
    val items: List<T>
)

class FeedWrapper<T : TMDbItem>(
    val feeds: List<T>,
    val sortTypeResourceId: Int,
    val sortType: SortType
)