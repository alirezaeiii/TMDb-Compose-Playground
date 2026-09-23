package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.Popular
import com.sample.tmdb.paging.main.BaseTvShowPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularTvSeriesViewModel @Inject constructor(@Popular repository: BasePagingRepository<TVShow>) :
    BaseTvShowPagingViewModel(repository)
