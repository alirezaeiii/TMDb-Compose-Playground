package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.utils.Async
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<DataType, Param>(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
) {

    protected abstract suspend fun getSuccessResult(isRefreshing: Boolean = false, id: Param? = null): DataType

    fun getResult(isRefreshing: Boolean = false, id: Param? = null): Flow<Async<DataType>> = execute(isRefreshing) {
        getSuccessResult(isRefreshing, id)
    }

    private fun execute(isRefreshing: Boolean, block: suspend () -> DataType): Flow<Async<DataType>> = flow {
        emit(Async.Loading(isRefreshing))
        try {
            emit(Async.Success(block()))
        } catch (_: Throwable) {
            emit(
                Async.Error(
                    context.getString(
                        if (isRefreshing) {
                            R.string.failed_refresh_msg
                        } else {
                            R.string.failed_loading_msg
                        },
                    ),
                    isRefreshing,
                ),
            )
        }
    }.flowOn(ioDispatcher)
}
