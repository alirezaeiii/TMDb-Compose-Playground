package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.utils.Async
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseCacheRepository<DataType, QueryType, FetchType>(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
    private val cacheDurationMs: Long = 60_000L,
) {

    private var lastRefreshTime: Long = 0L

    protected open fun getCurrentTime(): Long = System.currentTimeMillis()

    protected abstract suspend fun query(queryParam: QueryType?): DataType?

    protected abstract suspend fun fetch(fetchParam: FetchType?): DataType

    protected abstract suspend fun saveFetchResult(item: DataType)

    fun getResult(
        queryParam: QueryType? = null,
        fetchParam: FetchType? = null,
        forceRefresh: Boolean = true,
    ): Flow<Async<DataType>> = flow {
        emit(Async.Loading())
        when (val dbData = query(queryParam)) {
            null -> load(
                queryParam = queryParam,
                fetchParam = fetchParam,
                forceRefresh = forceRefresh,
            )

            is List<*> if dbData.isEmpty() -> load(
                queryParam = queryParam,
                fetchParam = fetchParam,
                forceRefresh = forceRefresh,
            )

            else -> load(dbData, queryParam, fetchParam, forceRefresh)
        }
    }.flowOn(ioDispatcher)

    private suspend fun FlowCollector<Async<DataType>>.load(
        dbData: DataType? = null,
        queryParam: QueryType?,
        fetchParam: FetchType?,
        forceRefresh: Boolean = true,
    ) {
        dbData?.let {
            // ****** VIEW CACHE ******
            emit(Async.Success(it))
        }
        try {
            if (forceRefresh || isCacheExpired()) {
                if (dbData != null) {
                    emit(Async.Loading(true))
                }
                // ****** MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                val fetchedData = refresh(fetchParam)
                lastRefreshTime = getCurrentTime()
                // ****** VIEW CACHE ******
                val cachedData = query(queryParam)
                val result = if (cachedData is List<*> &&
                    cachedData.isEmpty()
                ) {
                    fetchedData
                } else {
                    (cachedData ?: fetchedData)
                }
                emit(Async.Success(result))
            }
        } catch (_: Throwable) {
            emit(
                Async.Error(
                    context.getString(
                        if (dbData == null) R.string.failed_loading_msg else R.string.failed_refresh_msg,
                    ),
                    dbData != null,
                ),
            )
        }
    }

    private suspend fun refresh(fetchParam: FetchType?): DataType {
        val data = fetch(fetchParam)
        saveFetchResult(data)
        return data
    }

    private fun isCacheExpired(): Boolean = getCurrentTime() - lastRefreshTime > cacheDurationMs
}
