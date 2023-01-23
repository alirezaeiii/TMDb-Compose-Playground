package com.android.sample.tmdb.domain.model

import androidx.annotation.StringRes

class FeedWrapper<T : TMDbItem>(
    val feeds: List<T>,
    @StringRes val sortTypeResourceId: Int,
    val sortType: SortType
)