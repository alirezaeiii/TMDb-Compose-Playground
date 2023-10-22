package com.sample.tmdb.paging.main.movie

import com.sample.tmdb.domain.utils.Discover
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    @Discover repository: BasePagingRepository<Movie>
) : BaseMainPagingViewModel<Movie>(repository)