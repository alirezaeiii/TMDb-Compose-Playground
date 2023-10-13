package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.core.data.repository.TopRatedTvSeriesPagingRepository
import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedTvSeriesViewModel @Inject constructor(repository: TopRatedTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)