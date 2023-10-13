package com.sample.tmdb.core.data.repository

import android.content.Context
import com.sample.tmdb.core.data.network.TVShowService
import com.sample.tmdb.core.data.paging.BasePagingSource
import com.sample.tmdb.core.data.paging.tvshow.PopularTvSeriesPagingSource
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.core.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularTvSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService
) : BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?): BasePagingSource<TVShow> =
        PopularTvSeriesPagingSource(context, tvShowApi)
}