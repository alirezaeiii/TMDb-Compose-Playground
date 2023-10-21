package com.sample.tmdb.feed

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(repository: BaseFeedRepository<TVShow>) :
    BaseFeedViewModel<TVShow>(repository)