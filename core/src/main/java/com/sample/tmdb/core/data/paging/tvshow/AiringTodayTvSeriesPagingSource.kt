package com.sample.tmdb.core.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.core.data.network.TVShowService
import com.sample.tmdb.core.data.paging.BasePagingSource
import com.sample.tmdb.core.data.response.asTVShowDomainModel
import com.sample.tmdb.core.domain.model.TVShow

class AiringTodayTvSeriesPagingSource(
    context: Context,
    private val tvShowApi: TVShowService
) :
    BasePagingSource<TVShow>(context) {

    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.airingTodayTVSeries(page).items.asTVShowDomainModel()
}