package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.domain.annotations.NowPlaying
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AiringTodayTvSeriesViewModel @Inject constructor(
    @NowPlaying repository: BasePagingRepository<TVShow>
) : BaseMainPagingViewModel<TVShow>(repository)