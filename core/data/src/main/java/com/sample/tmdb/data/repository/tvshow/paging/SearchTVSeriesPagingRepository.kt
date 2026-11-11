package com.sample.tmdb.data.repository.tvshow.paging

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.tvshow.SearchTVSeriesRemoteMediator
import com.sample.tmdb.data.source.entity.SearchTVShowPagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.data.repository.BasePagingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchTVSeriesPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val tvShowApi: TVShowService,
    @ApplicationContext private val context: Context,
) : BasePagingRepository<TVShow, SearchTVShowPagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, SearchTVShowPagingEntity> =
        SearchTVSeriesRemoteMediator(database, tvShowApi, query!!, context)

    override fun createPagingSource(
        query: String?,
        id: Int?,
        type: SortType?,
    ): PagingSource<Int, SearchTVShowPagingEntity> = database.searchTVShowPagingDao.getPagingTVShows(query!!)

    override fun mapEntityToDomain(entity: SearchTVShowPagingEntity): TVShow = entity.asDomainModel()
}
