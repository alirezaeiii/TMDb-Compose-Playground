package com.sample.tmdb.ui.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.core.data.repository.SearchTvSeriesPagingRepository
import com.sample.tmdb.core.domain.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTvSeriesViewModel @Inject constructor(
    repository: SearchTvSeriesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)