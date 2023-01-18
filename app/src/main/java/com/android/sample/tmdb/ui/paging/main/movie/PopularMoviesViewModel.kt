package com.android.sample.tmdb.ui.paging.main.movie

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.PopularMoviesPagingRepository
import com.android.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(repository: PopularMoviesPagingRepository) :
    BaseMainPagingViewModel<Movie>(repository)