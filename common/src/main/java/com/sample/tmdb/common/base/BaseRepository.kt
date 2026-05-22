package com.sample.tmdb.common.base

import android.content.Context
import com.sample.tmdb.common.R
import com.sample.tmdb.common.utils.Async
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T, S>(private val context: Context, private val ioDispatcher: CoroutineDispatcher) {

    protected abstract suspend fun getSuccessResult(isRefreshing: Boolean = false, id: S? = null): T

    fun getResult(isRefreshing: Boolean = false, id: S? = null): Flow<Async<T>> = execute(isRefreshing) {
        getSuccessResult(isRefreshing, id)
    }

    private fun execute(isRefreshing: Boolean, block: suspend () -> T): Flow<Async<T>> = flow {
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
