package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource

class SearchTVSeriesPagingSource(context: Context, private val tvShowApi: TVShowService, private val query: String) :
    BasePagingSource<TVShow>(context) {
    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.searchTVSeries(page, query).items.asTVShowDomainModel()
}
