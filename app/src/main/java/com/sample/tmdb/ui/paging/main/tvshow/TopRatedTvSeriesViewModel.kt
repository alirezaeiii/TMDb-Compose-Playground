package com.sample.tmdb.ui.paging.main.tvshow

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.repository.TopRatedTvSeriesPagingRepository
import com.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedTvSeriesViewModel @Inject constructor(repository: TopRatedTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)