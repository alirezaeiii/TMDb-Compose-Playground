package com.sample.tmdb.ui.feed

import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.ui.BaseViewModel

open class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
) : BaseViewModel<List<FeedWrapper<T>>>(repository)
