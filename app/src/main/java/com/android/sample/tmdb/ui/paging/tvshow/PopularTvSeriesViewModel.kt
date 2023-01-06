package com.android.sample.tmdb.ui.paging.tvshow

import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.PopularTvSeriesPagingRepository
import com.android.sample.tmdb.ui.paging.BasePagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularTvSeriesViewModel @Inject constructor(repository: PopularTvSeriesPagingRepository) :
    BasePagingViewModel<TVShow>(repository)