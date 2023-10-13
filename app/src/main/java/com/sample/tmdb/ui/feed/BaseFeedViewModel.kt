package com.sample.tmdb.ui.feed

import com.sample.tmdb.common.base.BaseRefreshViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.core.domain.model.FeedWrapper
import com.sample.tmdb.core.domain.repository.BaseFeedRepository

open class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
) : BaseRefreshViewModel<List<FeedWrapper<T>>>(repository)
