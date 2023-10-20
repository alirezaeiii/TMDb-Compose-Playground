package com.sample.tmdb.paging.main.movie

import com.sample.tmdb.core.di.annotations.NowPlaying
import com.sample.tmdb.core.domain.model.Movie
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    @NowPlaying repository: BasePagingRepository<Movie>
) : BaseMainPagingViewModel<Movie>(repository)