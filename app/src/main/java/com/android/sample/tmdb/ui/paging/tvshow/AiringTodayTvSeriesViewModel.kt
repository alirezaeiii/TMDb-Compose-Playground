package com.android.sample.tmdb.ui.paging.tvshow

import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.AiringTodayTvSeriesPagingRepository
import com.android.sample.tmdb.ui.paging.BasePagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AiringTodayTvSeriesViewModel @Inject constructor(repository: AiringTodayTvSeriesPagingRepository) :
    BasePagingViewModel<TVShow>(repository)