package com.android.sample.tmdb.ui.paging.tvshow

import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.TrendingTvSeriesPagingRepository
import com.android.sample.tmdb.ui.paging.BasePagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(repository: TrendingTvSeriesPagingRepository) :
    BasePagingViewModel<TVShow>(repository)