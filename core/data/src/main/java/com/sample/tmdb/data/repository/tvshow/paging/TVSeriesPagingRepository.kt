package com.sample.tmdb.data.repository.tvshow.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.tvshow.TVShowRemoteMediator
import com.sample.tmdb.data.source.entity.TVShowPagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVSeriesPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val tvShowApi: TVShowService,
) : BasePagingRepositoryImpl<TVShow, TVShowPagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, TVShowPagingEntity> = TVShowRemoteMediator(database, tvShowApi, type!!, id)

    override fun createPagingSource(query: String?, id: Int?, type: SortType?): PagingSource<Int, TVShowPagingEntity> =
        database.tvShowPagingDao.getPagingTVShows(type!!)

    override fun mapEntityToDomain(entity: TVShowPagingEntity): TVShow = entity.asDomainModel()
}
