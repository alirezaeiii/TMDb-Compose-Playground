package com.sample.tmdb.domain.model

import androidx.annotation.StringRes
import com.sample.tmdb.common.model.TMDbItem

class FeedWrapper(
    val feeds: List<TMDbItem>,
    @StringRes val sortTypeResourceId: Int,
    val sortType: SortType,
)
