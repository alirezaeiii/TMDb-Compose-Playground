package com.sample.tmdb.ui.feed

import com.sample.tmdb.core.domain.model.Movie
import com.sample.tmdb.core.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(repository: BaseFeedRepository<Movie>) :
    BaseFeedViewModel<Movie>(repository)