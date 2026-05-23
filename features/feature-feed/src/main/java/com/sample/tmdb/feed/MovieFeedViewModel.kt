package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(
    repository: BaseFeedRepository<Movie>,
    languageRepository: LanguageRepository,
) : BaseViewModel<List<FeedWrapper>, Nothing>(repository, languageRepository = languageRepository)
