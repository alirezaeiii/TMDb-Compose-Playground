package com.android.sample.tmdb.ui.paging.main.tvshow

import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.TrendingTvSeriesPagingRepository
import com.android.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(repository: TrendingTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)