package com.sample.tmdb.ui.paging.main.tvshow

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.data.repository.TrendingTvSeriesPagingRepository
import com.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(repository: TrendingTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)