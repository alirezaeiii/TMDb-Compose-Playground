package com.sample.tmdb.ui.feed

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.repository.TVShowFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(repository: TVShowFeedRepository) :
    BaseFeedViewModel<TVShow>(repository)