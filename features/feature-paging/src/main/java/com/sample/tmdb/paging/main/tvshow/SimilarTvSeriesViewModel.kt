package com.sample.tmdb.paging.main.tvshow

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimilarTvSeriesViewModel
@Inject
constructor(
    @Similar repository: BasePagingRepository<TVShow>,
    savedStateHandle: SavedStateHandle,
) : BaseMainPagingViewModel<TVShow>(repository, savedStateHandle[MainDestinations.TMDB_SIMILAR_ID])
