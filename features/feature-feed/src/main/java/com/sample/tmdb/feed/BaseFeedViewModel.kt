package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.repository.BaseFeedRepository

open class BaseFeedViewModel<T : TMDbItem>(repository: BaseFeedRepository<T>, languageRepository: LanguageRepository) :
    BaseViewModel<List<FeedWrapper>, Nothing>(repository, languageRepository)
