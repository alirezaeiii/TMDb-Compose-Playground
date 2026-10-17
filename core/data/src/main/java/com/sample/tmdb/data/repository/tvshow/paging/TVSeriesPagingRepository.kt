package com.sample.tmdb.data.repository.tvshow.paging

import android.content.Context
import androidx.paging.PagingSource
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.tvshow.TVSeriesPagingSource
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.SortType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService,
) : BasePagingRepository<TVShow>() {

    override fun createPagingSource(query: String?, id: Int?, type: SortType?): PagingSource<Int, TVShow> =
        TVSeriesPagingSource(context, tvShowApi, type!!, id)
}
