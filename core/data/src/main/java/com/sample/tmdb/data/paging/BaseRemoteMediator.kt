package com.sample.tmdb.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sample.tmdb.data.source.local.TMDbDatabase
import java.io.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator<T : Any, K : Any, E : Any>(private val database: TMDbDatabase) :
    RemoteMediator<Int, T>() {

    protected abstract suspend fun fetchItems(page: Int): List<T>

    protected abstract fun getRemoteKeyId(item: T): K

    protected abstract suspend fun getRemoteKey(id: K): E?

    protected abstract fun getPrevKey(remoteKey: E): Int?

    protected abstract fun getNextKey(remoteKey: E): Int?

    protected abstract fun createRemoteKeys(items: List<T>, prevKey: Int?, nextKey: Int?): List<E>

    protected abstract suspend fun clearLocalData()

    protected abstract suspend fun saveLocalData(keys: List<E>, items: List<T>)

    override suspend fun load(loadType: LoadType, state: PagingState<Int, T>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                getRemoteKeyClosestToCurrentPosition(state)
                    ?.let(::getNextKey)
                    ?.minus(1)
                    ?: STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)

                val prevKey = remoteKeys?.let(::getPrevKey)
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null,
                    )

                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)

                val nextKey = remoteKeys?.let(::getNextKey)
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null,
                    )

                nextKey
            }
        }

        return try {
            val response = fetchItems(page)
            val endOfPaginationReached = response.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    clearLocalData()
                }

                val prevKey =
                    if (page == STARTING_PAGE_INDEX) null else page - 1

                val nextKey =
                    if (endOfPaginationReached) null else page + 1

                val keys = createRemoteKeys(
                    items = response,
                    prevKey = prevKey,
                    nextKey = nextKey,
                )

                saveLocalData(
                    keys = keys,
                    items = response,
                )
            }

            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached,
            )
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, T>): E? = state.pages
        .lastOrNull { it.data.isNotEmpty() }
        ?.data
        ?.lastOrNull()
        ?.let { item ->
            getRemoteKey(getRemoteKeyId(item))
        }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, T>): E? = state.pages
        .firstOrNull { it.data.isNotEmpty() }
        ?.data
        ?.firstOrNull()
        ?.let { item ->
            getRemoteKey(getRemoteKeyId(item))
        }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, T>): E? = state.anchorPosition
        ?.let(state::closestItemToPosition)
        ?.let { item ->
            getRemoteKey(getRemoteKeyId(item))
        }
}
