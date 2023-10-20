package com.sample.tmdb.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.core.di.annotations.Search
import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTvSeriesViewModel @Inject constructor(
    @Search repository: BasePagingRepository<TVShow>,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)