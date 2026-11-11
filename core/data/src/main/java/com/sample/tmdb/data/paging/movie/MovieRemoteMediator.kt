package com.sample.tmdb.data.paging.movie

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.BaseRemoteMediator
import com.sample.tmdb.data.response.asMoviePagingDatabaseModel
import com.sample.tmdb.data.source.entity.MoviePagingEntity
import com.sample.tmdb.data.source.entity.MovieRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.SortType

class MovieRemoteMediator(
    private val database: TMDbDatabase,
    private val movieApi: MovieService,
    private val sortType: SortType,
    private val movieId: Int? = null,
    context: Context
) : BaseRemoteMediator<MoviePagingEntity, SortType, MovieRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<MoviePagingEntity> = when (sortType) {
        SortType.TRENDING -> movieApi.trendingMovies(page)
        SortType.DISCOVER -> movieApi.discoverMovies(page)
        SortType.MOST_POPULAR -> movieApi.popularMovies(page)
        SortType.NOW_PLAYING -> movieApi.nowPlayingMovies(page)
        SortType.UPCOMING -> movieApi.upcomingMovies(page)
        SortType.HIGHEST_RATED -> movieApi.topRatedMovies(page)
        SortType.SIMILAR -> movieApi.fetchSimilarMovies(movieId!!, page)
        SortType.APPLE_TV -> movieApi.providerMovies(APPLE_TV_PROVIDER_ID, page)
        SortType.NETFLIX -> movieApi.providerMovies(NETFLIX_PROVIDER_ID, page)
        SortType.AMAZON_PRIME -> movieApi.providerMovies(AMAZON_PRIME_PROVIDER_ID, page)
        SortType.GOOGLE_PLAY -> movieApi.providerMovies(GOOGLE_PLAY_PROVIDER_ID, page)
    }.items.asMoviePagingDatabaseModel(sortType, page)

    override fun getRemoteKeyId(item: MoviePagingEntity): SortType = sortType

    override suspend fun getRemoteKey(id: SortType): MovieRemoteKeyEntity? = database.movieRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: MovieRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: MovieRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<MoviePagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<MovieRemoteKeyEntity> = listOf(
        MovieRemoteKeyEntity(
            sortType = sortType,
            prevKey = prevKey,
            nextKey = nextKey,
        ),
    )

    override suspend fun clearLocalData() {
        database.moviePagingDao.deleteBySortType(sortType)
        database.movieRemoteKeyDao.deleteRemoteKey(sortType)
    }

    override suspend fun saveLocalData(keys: List<MovieRemoteKeyEntity>, items: List<MoviePagingEntity>) {
        database.moviePagingDao.insertAll(items)
        database.movieRemoteKeyDao.insertRemoteKey(keys.first())
    }
}
