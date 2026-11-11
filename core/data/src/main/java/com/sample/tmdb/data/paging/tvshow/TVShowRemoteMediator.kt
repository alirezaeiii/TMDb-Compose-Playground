package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BaseRemoteMediator
import com.sample.tmdb.data.response.asTVShowPagingDatabaseModel
import com.sample.tmdb.data.source.entity.TVShowPagingEntity
import com.sample.tmdb.data.source.entity.TVShowRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.SortType

class TVShowRemoteMediator(
    private val database: TMDbDatabase,
    private val tvShowApi: TVShowService,
    private val sortType: SortType,
    private val tvShowId: Int? = null,
    context: Context,
) : BaseRemoteMediator<TVShowPagingEntity, SortType, TVShowRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<TVShowPagingEntity> = when (sortType) {
        SortType.TRENDING -> tvShowApi.trendingTVSeries(page)

        SortType.DISCOVER -> tvShowApi.discoverTVSeries(page)

        SortType.MOST_POPULAR -> tvShowApi.popularTVSeries(page)

        SortType.NOW_PLAYING -> tvShowApi.airingTodayTVSeries(page)

        SortType.UPCOMING -> tvShowApi.onTheAirTVSeries(page)

        SortType.HIGHEST_RATED -> tvShowApi.topRatedTVSeries(page)

        SortType.SIMILAR -> tvShowApi.fetchSimilarTVSeries(tvShowId!!, page)

        SortType.APPLE_TV -> tvShowApi.providerTVSeries(APPLE_TV_PROVIDER_ID, page)

        SortType.NETFLIX -> tvShowApi.providerTVSeries(NETFLIX_PROVIDER_ID, page)

        SortType.AMAZON_PRIME -> tvShowApi.providerTVSeries(
            AMAZON_PRIME_PROVIDER_ID,
            page,
        )

        SortType.GOOGLE_PLAY -> tvShowApi.providerTVSeries(
            GOOGLE_PLAY_PROVIDER_ID,
            page,
        )
    }.items.asTVShowPagingDatabaseModel(sortType, page)

    override fun getRemoteKeyId(item: TVShowPagingEntity): SortType = sortType

    override suspend fun getRemoteKey(id: SortType): TVShowRemoteKeyEntity? =
        database.tvShowRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: TVShowRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: TVShowRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<TVShowPagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<TVShowRemoteKeyEntity> = listOf(
        TVShowRemoteKeyEntity(
            sortType,
            prevKey,
            nextKey,
        ),
    )

    override suspend fun clearLocalData() {
        database.tvShowRemoteKeyDao.deleteRemoteKey(sortType)
        database.tvShowPagingDao.deleteBySortType(sortType)
    }

    override suspend fun saveLocalData(keys: List<TVShowRemoteKeyEntity>, items: List<TVShowPagingEntity>) {
        database.tvShowRemoteKeyDao.insertRemoteKey(keys.first())
        database.tvShowPagingDao.insertAll(items)
    }
}
