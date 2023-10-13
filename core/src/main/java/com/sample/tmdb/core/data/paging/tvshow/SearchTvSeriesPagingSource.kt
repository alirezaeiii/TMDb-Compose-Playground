package com.sample.tmdb.core.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.core.data.network.TVShowService
import com.sample.tmdb.core.data.paging.BasePagingSource
import com.sample.tmdb.core.data.response.asTVShowDomainModel
import com.sample.tmdb.core.domain.model.TVShow

class SearchTvSeriesPagingSource(
    context: Context,
    private val tvShowApi: TVShowService,
    private val query: String
) : BasePagingSource<TVShow>(context) {

    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.searchTVSeries(page, query).items.asTVShowDomainModel()
}