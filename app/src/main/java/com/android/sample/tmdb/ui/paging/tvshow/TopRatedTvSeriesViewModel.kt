package com.android.sample.tmdb.ui.paging.tvshow

import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.TopRatedTvSeriesPagingRepository
import com.android.sample.tmdb.ui.paging.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedTvSeriesViewModel @Inject constructor(repository: TopRatedTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)