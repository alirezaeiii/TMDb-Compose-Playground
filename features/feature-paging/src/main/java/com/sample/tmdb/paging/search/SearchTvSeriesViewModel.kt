package com.sample.tmdb.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.utils.Search
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTvSeriesViewModel @Inject constructor(
    @Search repository: BasePagingRepository<TVShow>,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)