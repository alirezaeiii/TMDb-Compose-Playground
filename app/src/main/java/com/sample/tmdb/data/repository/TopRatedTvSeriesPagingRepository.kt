package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BasePagingSource
import com.sample.tmdb.data.paging.tvshow.TopRatedTvSeriesPagingSource
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedTvSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService
) : BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?): BasePagingSource<TVShow> =
        TopRatedTvSeriesPagingSource(context, tvShowApi)
}