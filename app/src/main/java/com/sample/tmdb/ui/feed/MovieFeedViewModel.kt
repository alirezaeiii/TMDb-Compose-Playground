package com.sample.tmdb.ui.feed

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.repository.MovieFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(repository: MovieFeedRepository) :
    BaseFeedViewModel<Movie>(repository)