package com.android.sample.tmdb.ui.feed

import com.android.sample.tmdb.data.TVShow
import com.android.sample.tmdb.repository.TVShowFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(repository: TVShowFeedRepository) :
    BaseFeedViewModel<TVShow>(repository)