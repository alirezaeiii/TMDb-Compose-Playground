package com.android.sample.tmdb.domain

import android.content.Context
import com.android.sample.tmdb.R
import com.android.sample.tmdb.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T>(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) {
    protected abstract suspend fun successResult(id: Any?): Resource<T>

    fun getResult(id: Any?): Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        try {
            emit(successResult(id))
        } catch (t: Throwable) {
            emit(Resource.Error(context.getString(R.string.failed_loading_msg)))
        }
    }.flowOn(ioDispatcher)
}