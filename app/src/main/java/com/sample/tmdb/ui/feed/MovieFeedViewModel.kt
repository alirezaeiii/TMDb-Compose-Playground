package com.sample.tmdb.ui.feed

import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(repository: BaseFeedRepository<Movie>) :
    BaseFeedViewModel<Movie>(repository)