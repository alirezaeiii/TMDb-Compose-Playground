package com.android.sample.tmdb.ui.paging.main.movie

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.UpcomingMoviesPagingRepository
import com.android.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(repository: UpcomingMoviesPagingRepository) :
    BaseMainPagingViewModel<Movie>(repository)