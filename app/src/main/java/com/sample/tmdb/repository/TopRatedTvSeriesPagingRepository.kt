package com.sample.tmdb.repository

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paged.BasePagingSource
import com.sample.tmdb.data.paged.tvshow.TopRatedTvSeriesPagingSource
import com.sample.tmdb.domain.BasePagingRepository
import com.sample.tmdb.domain.model.TVShow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedTvSeriesPagingRepository @Inject constructor(
    private val context: Context,
    private val tvShowApi: TVShowService
) : BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?): BasePagingSource<TVShow> =
        TopRatedTvSeriesPagingSource(context, tvShowApi)
}