package com.sample.tmdb.data.di

import com.sample.tmdb.data.utils.CacheTimestampDataStoreImpl
import com.sample.tmdb.domain.utils.CacheTimestampDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun bindCacheTimestampDataStore(implementation: CacheTimestampDataStoreImpl): CacheTimestampDataStore
}
