package com.android.sample.tmdb.ui.paging.movie

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.UpcomingMoviesPagingRepository
import com.android.sample.tmdb.ui.paging.BasePagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(repository: UpcomingMoviesPagingRepository) :
    BasePagingViewModel<Movie>(repository)