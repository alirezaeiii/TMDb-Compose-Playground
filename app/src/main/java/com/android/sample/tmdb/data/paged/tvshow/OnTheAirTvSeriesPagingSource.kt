package com.android.sample.tmdb.data.paged.tvshow

import com.android.sample.tmdb.data.network.TVShowService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.response.asTVShowDomainModel
import com.android.sample.tmdb.domain.model.TVShow

class OnTheAirTvSeriesPagingSource(private val tvShowApi: TVShowService) :
    BasePagingSource<TVShow>() {

    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.onTheAirTVSeries(page).items.asTVShowDomainModel()
}