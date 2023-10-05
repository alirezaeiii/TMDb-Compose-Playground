package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BasePagingSource
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.TVShow

class AiringTodayTvSeriesPagingSource(
    context: Context,
    private val tvShowApi: TVShowService
) :
    BasePagingSource<TVShow>(context) {

    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.airingTodayTVSeries(page).items.asTVShowDomainModel()
}