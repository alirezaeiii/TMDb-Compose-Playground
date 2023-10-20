package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.core.di.annotations.Discover
import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverTvSeriesViewModel @Inject constructor(
    @Discover repository: BasePagingRepository<TVShow>
) : BaseMainPagingViewModel<TVShow>(repository)