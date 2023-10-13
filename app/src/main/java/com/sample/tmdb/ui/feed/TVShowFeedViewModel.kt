package com.sample.tmdb.ui.feed

import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.core.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(repository: BaseFeedRepository<TVShow>) :
    BaseFeedViewModel<TVShow>(repository)