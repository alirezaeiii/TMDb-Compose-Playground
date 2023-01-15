package com.android.sample.tmdb.ui.paging.movie

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.TopRatedMoviesPagingRepository
import com.android.sample.tmdb.ui.paging.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(repository: TopRatedMoviesPagingRepository) :
    BaseMainPagingViewModel<Movie>(repository)