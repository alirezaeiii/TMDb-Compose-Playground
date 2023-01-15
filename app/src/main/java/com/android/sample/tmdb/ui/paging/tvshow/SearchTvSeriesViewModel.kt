package com.android.sample.tmdb.ui.paging.tvshow

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.SearchTvSeriesPagingRepository
import com.android.sample.tmdb.ui.paging.BaseSearchPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTvSeriesViewModel @Inject constructor(
    repository: SearchTvSeriesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)