package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.core.data.repository.TrendingTvSeriesPagingRepository
import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(repository: TrendingTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)