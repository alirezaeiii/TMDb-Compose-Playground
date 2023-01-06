package com.android.sample.tmdb.ui.paging.movie

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.PopularMoviesPagingRepository
import com.android.sample.tmdb.ui.paging.BasePagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(repository: PopularMoviesPagingRepository) :
    BasePagingViewModel<Movie>(repository)