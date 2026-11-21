package com.sample.tmdb.domain.model

import androidx.annotation.StringRes

class Feed(val feedWrapper: List<FeedWrapper>, val genres: List<Genre>)

class FeedWrapper(val feeds: List<TMDbItem>, @StringRes val sortTypeResourceId: Int, val sortType: SortType)
