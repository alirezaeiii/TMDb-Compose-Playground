package com.sample.tmdb.domain.utils

interface CacheTimestampDataStore {
    suspend fun getLastRefreshTime(key: String): Long

    suspend fun saveLastRefreshTime(key: String, timestamp: Long)
}
