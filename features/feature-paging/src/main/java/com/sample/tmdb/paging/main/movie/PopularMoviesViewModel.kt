package com.sample.tmdb.paging.main.movie

import com.sample.tmdb.domain.utils.Popular
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    @Popular repository: BasePagingRepository<Movie>
) : BaseMainPagingViewModel<Movie>(repository)