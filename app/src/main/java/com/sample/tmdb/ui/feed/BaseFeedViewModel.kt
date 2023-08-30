package com.sample.tmdb.ui.feed

import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.ui.BaseViewModelInit

open class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
) : BaseViewModelInit<List<FeedWrapper<T>>>(repository)
