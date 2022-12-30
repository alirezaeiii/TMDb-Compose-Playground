package com.android.sample.tmdb.ui.feed

import com.android.sample.tmdb.domain.BaseFeedRepository
import com.android.sample.tmdb.domain.model.FeedWrapper
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.BaseViewModel

open class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
) : BaseViewModel<List<FeedWrapper<T>>>(repository)
