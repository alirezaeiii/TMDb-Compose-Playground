package com.sample.tmdb.paging.main.movie

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel
    @Inject
    constructor(
        @Similar repository: BasePagingRepository<Movie>,
        savedStateHandle: SavedStateHandle,
    ) : BaseMainPagingViewModel<Movie>(repository, savedStateHandle[MainDestinations.TMDB_SIMILAR_ID])
