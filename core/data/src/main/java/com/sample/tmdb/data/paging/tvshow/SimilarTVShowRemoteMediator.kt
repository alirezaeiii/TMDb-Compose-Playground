package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BaseRemoteMediator
import com.sample.tmdb.data.response.asSimilarTVShowPagingDatabaseModel
import com.sample.tmdb.data.source.entity.SimilarTVShowPagingEntity
import com.sample.tmdb.data.source.entity.SimilarTVShowRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase

class SimilarTVShowRemoteMediator(
    private val database: TMDbDatabase,
    private val tvShowApi: TVShowService,
    private val tvShowId: Int,
    context: Context,
) : BaseRemoteMediator<SimilarTVShowPagingEntity, Int, SimilarTVShowRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<SimilarTVShowPagingEntity> =
        tvShowApi.fetchSimilarTVSeries(tvShowId, page).items.asSimilarTVShowPagingDatabaseModel(tvShowId, page)

    override fun getRemoteKeyId(item: SimilarTVShowPagingEntity): Int = tvShowId

    override suspend fun clearLocalData() {
        database.similarTVShowPagingDao.deleteMovies(tvShowId)
        database.similarTVShowRemoteKeyDao.deleteRemoteKey(tvShowId)
    }

    override suspend fun saveLocalData(
        keys: List<SimilarTVShowRemoteKeyEntity>,
        items: List<SimilarTVShowPagingEntity>,
    ) {
        database.similarTVShowPagingDao.insertAll(items)
        database.similarTVShowRemoteKeyDao.insertRemoteKey(keys.first())
    }

    override suspend fun getRemoteKey(id: Int): SimilarTVShowRemoteKeyEntity? =
        database.similarTVShowRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: SimilarTVShowRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: SimilarTVShowRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<SimilarTVShowPagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<SimilarTVShowRemoteKeyEntity> = listOf(
        SimilarTVShowRemoteKeyEntity(
            id = tvShowId,
            prevKey = prevKey,
            nextKey = nextKey,
        ),
    )
}
