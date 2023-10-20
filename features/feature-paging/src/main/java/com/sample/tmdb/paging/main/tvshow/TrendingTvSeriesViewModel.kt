package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.core.di.annotations.Trending
import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(
    @Trending repository: BasePagingRepository<TVShow>
) : BaseMainPagingViewModel<TVShow>(repository)