package com.sample.tmdb.paging.main.movie

import com.sample.tmdb.core.data.repository.PopularMoviesPagingRepository
import com.sample.tmdb.core.domain.model.Movie
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(repository: PopularMoviesPagingRepository) :
    BaseMainPagingViewModel<Movie>(repository)