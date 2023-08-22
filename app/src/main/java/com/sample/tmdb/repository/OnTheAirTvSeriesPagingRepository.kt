package com.sample.tmdb.repository

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paged.BasePagingSource
import com.sample.tmdb.data.paged.tvshow.AiringTodayTvSeriesPagingSource
import com.sample.tmdb.data.paged.tvshow.OnTheAirTvSeriesPagingSource
import com.sample.tmdb.domain.BasePagingRepository
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnTheAirTvSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService
) : BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?): BasePagingSource<TVShow> =
        OnTheAirTvSeriesPagingSource(context, tvShowApi)
}