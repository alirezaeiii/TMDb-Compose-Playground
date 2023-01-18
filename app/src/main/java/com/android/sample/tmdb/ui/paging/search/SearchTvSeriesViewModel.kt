package com.android.sample.tmdb.ui.paging.search

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.SearchTvSeriesPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTvSeriesViewModel @Inject constructor(
    repository: SearchTvSeriesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)