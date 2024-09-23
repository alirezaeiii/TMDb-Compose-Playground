package com.sample.tmdb.feed

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel
    @Inject
    constructor(
        repository: BaseFeedRepository<Movie>,
    ) : BaseFeedViewModel<Movie>(repository)
