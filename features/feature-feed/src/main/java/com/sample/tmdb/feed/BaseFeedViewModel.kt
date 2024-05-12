package com.sample.tmdb.feed

import com.sample.tmdb.common.base.TMDbViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.repository.BaseFeedRepository

open class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
) : TMDbViewModel<List<FeedWrapper>>(repository)
