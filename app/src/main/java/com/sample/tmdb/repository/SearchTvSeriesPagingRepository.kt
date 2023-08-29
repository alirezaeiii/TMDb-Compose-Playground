package com.sample.tmdb.repository

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paged.BasePagingSource
import com.sample.tmdb.data.paged.tvshow.SearchTvSeriesPagingSource
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchTvSeriesPagingRepository@Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService
) : BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?): BasePagingSource<TVShow> =
        SearchTvSeriesPagingSource(context, tvShowApi, query!!)
}