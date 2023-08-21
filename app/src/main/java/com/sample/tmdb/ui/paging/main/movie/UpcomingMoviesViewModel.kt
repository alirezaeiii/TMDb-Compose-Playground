package com.sample.tmdb.ui.paging.main.movie

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.repository.UpcomingMoviesPagingRepository
import com.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(repository: UpcomingMoviesPagingRepository) :
    BaseMainPagingViewModel<Movie>(repository)