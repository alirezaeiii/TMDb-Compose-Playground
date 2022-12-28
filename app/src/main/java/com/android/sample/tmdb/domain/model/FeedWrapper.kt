package com.android.sample.tmdb.domain.model

class FeedWrapper<T : TMDbItem>(
    val feeds: List<T>,
    val sortTypeResourceId: Int,
    val sortType: SortType
)