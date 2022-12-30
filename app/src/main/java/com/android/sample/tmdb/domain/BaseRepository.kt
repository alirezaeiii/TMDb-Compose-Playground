package com.android.sample.tmdb.domain

import com.android.sample.tmdb.utils.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseRepository<T> {
    abstract fun getResult(id: Any?): Flow<Resource<T>>
}