package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BaseRemoteMediator
import com.sample.tmdb.data.response.asTVShowSearchPagingDatabaseModel
import com.sample.tmdb.data.source.entity.SearchTVShowPagingEntity
import com.sample.tmdb.data.source.entity.SearchTVShowRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase

class SearchTVSeriesRemoteMediator(
    private val database: TMDbDatabase,
    private val tvShowApi: TVShowService,
    private val query: String,
    context: Context,
) : BaseRemoteMediator<SearchTVShowPagingEntity, String, SearchTVShowRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<SearchTVShowPagingEntity> =
        tvShowApi.searchTVSeries(page, query).items.asTVShowSearchPagingDatabaseModel(query, page)

    override fun getRemoteKeyId(item: SearchTVShowPagingEntity): String = query

    override suspend fun getRemoteKey(id: String): SearchTVShowRemoteKeyEntity? =
        database.searchTVShowRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: SearchTVShowRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: SearchTVShowRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<SearchTVShowPagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<SearchTVShowRemoteKeyEntity> = listOf(
        SearchTVShowRemoteKeyEntity(
            query,
            prevKey,
            nextKey,
        ),
    )

    override suspend fun clearLocalData() {
        database.searchTVShowRemoteKeyDao.deleteRemoteKey(query)
        database.searchTVShowPagingDao.deleteTVShows(query)
    }

    override suspend fun saveLocalData(keys: List<SearchTVShowRemoteKeyEntity>, items: List<SearchTVShowPagingEntity>) {
        database.searchTVShowRemoteKeyDao.insertRemoteKey(keys.first())
        database.searchTVShowPagingDao.insertTVShows(items)
    }
}
