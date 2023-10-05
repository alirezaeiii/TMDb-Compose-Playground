package com.sample.tmdb.ui.paging.main.tvshow

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.data.repository.AiringTodayTvSeriesPagingRepository
import com.sample.tmdb.ui.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AiringTodayTvSeriesViewModel @Inject constructor(repository: AiringTodayTvSeriesPagingRepository) :
    BaseMainPagingViewModel<TVShow>(repository)