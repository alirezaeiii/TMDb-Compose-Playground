package com.sample.tmdb.data.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sample.tmdb.domain.utils.CacheTimestampDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first

@Singleton
class DataStoreCacheTimestamp @Inject constructor(@ApplicationContext context: Context) : CacheTimestampDataStore {

    private val dataStore = context.cacheTimestampDataStore

    override suspend fun getLastRefreshTime(key: String): Long = dataStore.data.first()[longPreferencesKey(key)] ?: 0L

    override suspend fun saveLastRefreshTime(key: String, timestamp: Long) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = timestamp
        }
    }
}

private val Context.cacheTimestampDataStore by preferencesDataStore(
    name = "cache_timestamps",
)
