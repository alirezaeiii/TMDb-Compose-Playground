package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource

class TopRatedTVSeriesPagingSource(context: Context, private val tvShowApi: TVShowService) :
    BasePagingSource<TVShow>(context) {
    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.topRatedTVSeries(page).items.asTVShowDomainModel()
}
