package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.domain.annotations.TopRated
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedTvSeriesViewModel @Inject constructor(
    @TopRated repository: BasePagingRepository<TVShow>
) : BaseMainPagingViewModel<TVShow>(repository)