package com.sample.tmdb.data.paging.movie

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.BaseRemoteMediator
import com.sample.tmdb.data.response.asSimilarMoviePagingDatabaseModel
import com.sample.tmdb.data.source.entity.SimilarMoviePagingEntity
import com.sample.tmdb.data.source.entity.SimilarMovieRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase

class SimilarMovieRemoteMediator(
    private val database: TMDbDatabase,
    private val movieApi: MovieService,
    private val movieId: Int,
    context: Context,
) : BaseRemoteMediator<SimilarMoviePagingEntity, Int, SimilarMovieRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<SimilarMoviePagingEntity> =
        movieApi.fetchSimilarMovies(movieId, page).items.asSimilarMoviePagingDatabaseModel(movieId, page)

    override fun getRemoteKeyId(item: SimilarMoviePagingEntity): Int = movieId

    override suspend fun clearLocalData() {
        database.similarMoviePagingDao.deleteMovies(movieId)
        database.similarMovieRemoteKeyDao.deleteRemoteKey(movieId)
    }

    override suspend fun saveLocalData(keys: List<SimilarMovieRemoteKeyEntity>, items: List<SimilarMoviePagingEntity>) {
        database.similarMoviePagingDao.insertAll(items)
        database.similarMovieRemoteKeyDao.insertRemoteKey(keys.first())
    }

    override suspend fun getRemoteKey(id: Int): SimilarMovieRemoteKeyEntity? =
        database.similarMovieRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: SimilarMovieRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: SimilarMovieRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<SimilarMoviePagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<SimilarMovieRemoteKeyEntity> = listOf(
        SimilarMovieRemoteKeyEntity(
            id = movieId,
            prevKey = prevKey,
            nextKey = nextKey,
        ),
    )
}
