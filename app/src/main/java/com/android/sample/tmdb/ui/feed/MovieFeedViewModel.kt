package com.android.sample.tmdb.ui.feed

import com.android.sample.tmdb.data.Movie
import com.android.sample.tmdb.repository.MovieFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(repository: MovieFeedRepository) :
    BaseFeedViewModel<Movie>(repository)