package com.sample.tmdb.core.domain.model

import androidx.annotation.StringRes
import com.sample.tmdb.common.model.TMDbItem

class FeedWrapper<T : TMDbItem>(
    val feeds: List<T>,
    @StringRes val sortTypeResourceId: Int,
    val sortType: SortType
)