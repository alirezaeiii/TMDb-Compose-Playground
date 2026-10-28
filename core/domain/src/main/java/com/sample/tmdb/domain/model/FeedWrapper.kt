package com.sample.tmdb.domain.model

import androidx.annotation.StringRes

class FeedWrapper(val feeds: List<TMDbItem>, @StringRes val sortTypeResourceId: Int, val sortType: SortType)
