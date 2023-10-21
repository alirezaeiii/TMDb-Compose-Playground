package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseRefreshViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.repository.BaseFeedRepository

open class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
) : BaseRefreshViewModel<List<FeedWrapper<T>>>(repository)
