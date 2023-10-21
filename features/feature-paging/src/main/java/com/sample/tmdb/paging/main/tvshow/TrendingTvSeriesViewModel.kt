package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.domain.annotations.Trending
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(
    @Trending repository: BasePagingRepository<TVShow>
) : BaseMainPagingViewModel<TVShow>(repository)