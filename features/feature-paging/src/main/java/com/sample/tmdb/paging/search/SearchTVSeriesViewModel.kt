package com.sample.tmdb.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.PagingRepository
import com.sample.tmdb.domain.utils.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTVSeriesViewModel @Inject constructor(
    @Search repository: PagingRepository<TVShow>,
    savedStateHandle: SavedStateHandle,
) : BaseSearchTMDbPagingViewModel<TVShow>(repository, savedStateHandle)
