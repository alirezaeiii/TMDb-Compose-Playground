package com.sample.tmdb.data.repository.tvshow.paging

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.tvshow.TrendingTvSeriesPagingSource
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingTvSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService
) : BasePagingRepository<TVShow>() {

    override fun pagingSource(query: String?, id: Int?): BasePagingSource<TVShow> =
        TrendingTvSeriesPagingSource(context, tvShowApi)
}