package com.sample.tmdb.data.paging.movie

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.BaseRemoteMediator
import com.sample.tmdb.data.response.asMovieSearchPagingDatabaseModel
import com.sample.tmdb.data.source.entity.SearchMoviePagingEntity
import com.sample.tmdb.data.source.entity.SearchMovieRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase

class SearchMovieRemoteMediator(
    private val database: TMDbDatabase,
    private val movieApi: MovieService,
    private val query: String,
    context: Context,
) : BaseRemoteMediator<SearchMoviePagingEntity, String, SearchMovieRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<SearchMoviePagingEntity> =
        movieApi.searchMovies(page, query).items.asMovieSearchPagingDatabaseModel(query, page)

    override fun getRemoteKeyId(item: SearchMoviePagingEntity): String = query

    override suspend fun getRemoteKey(id: String): SearchMovieRemoteKeyEntity? =
        database.searchMovieRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: SearchMovieRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: SearchMovieRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<SearchMoviePagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<SearchMovieRemoteKeyEntity> = listOf(
        SearchMovieRemoteKeyEntity(
            queryStr = query,
            prevKey = prevKey,
            nextKey = nextKey,
        ),
    )

    override suspend fun clearLocalData() {
        database.searchMoviesPagingDao.deleteMovies(query)
        database.searchMovieRemoteKeyDao.deleteRemoteKey(query)
    }

    override suspend fun saveLocalData(keys: List<SearchMovieRemoteKeyEntity>, items: List<SearchMoviePagingEntity>) {
        database.searchMoviesPagingDao.insertMovies(items)
        database.searchMovieRemoteKeyDao.insertRemoteKey(keys.first())
    }
}
