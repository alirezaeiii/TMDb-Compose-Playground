package com.sample.tmdb.data.repository.tvshow.paging

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.tvshow.SimilarTVShowRemoteMediator
import com.sample.tmdb.data.repository.BasePagingRepository
import com.sample.tmdb.data.source.entity.SimilarTVShowPagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimilarTVSeriesPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val tvShowApi: TVShowService,
    @ApplicationContext private val context: Context,
) : BasePagingRepository<TVShow, SimilarTVShowPagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, SimilarTVShowPagingEntity> = SimilarTVShowRemoteMediator(database, tvShowApi, id!!, context)

    override fun createPagingSource(
        query: String?,
        id: Int?,
        type: SortType?,
    ): PagingSource<Int, SimilarTVShowPagingEntity> = database.similarTVShowPagingDao.getPagingTVShows(id!!)

    override fun mapEntityToDomain(entity: SimilarTVShowPagingEntity): TVShow = entity.asDomainModel()
}
