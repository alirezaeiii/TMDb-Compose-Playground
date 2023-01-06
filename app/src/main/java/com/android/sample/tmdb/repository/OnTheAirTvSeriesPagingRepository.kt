package com.android.sample.tmdb.repository

import com.android.sample.tmdb.data.network.TVShowService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.paged.tvshow.OnTheAirTvSeriesPagingSource
import com.android.sample.tmdb.domain.BasePagingRepository
import com.android.sample.tmdb.domain.model.TVShow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnTheAirTvSeriesPagingRepository @Inject constructor(private val tvShowApi: TVShowService) :
    BasePagingRepository<TVShow>() {

    override val pagingSource: BasePagingSource<TVShow>
        get() = OnTheAirTvSeriesPagingSource(tvShowApi)
}