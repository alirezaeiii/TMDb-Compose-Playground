package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.data.network.TVShowService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.paged.tvshow.AiringTodayTvSeriesPagingSource
import com.android.sample.tmdb.domain.BasePagingRepository
import com.android.sample.tmdb.domain.model.TVShow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiringTodayTvSeriesPagingRepository @Inject constructor(
    private val context: Context,
    private val tvShowApi: TVShowService) :
    BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?): BasePagingSource<TVShow> =
        AiringTodayTvSeriesPagingSource(context, tvShowApi)
}