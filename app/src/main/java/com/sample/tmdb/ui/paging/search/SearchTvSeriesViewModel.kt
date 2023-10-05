package com.sample.tmdb.ui.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.data.repository.SearchTvSeriesPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTvSeriesViewModel @Inject constructor(
    repository: SearchTvSeriesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)