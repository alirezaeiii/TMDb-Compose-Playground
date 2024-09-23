package com.sample.tmdb.data.di

import android.content.Context
import androidx.room.Room
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.data.source.local.TVShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideTMDbDatabase(
        @ApplicationContext context: Context,
    ): TMDbDatabase =
        Room
            .databaseBuilder(
                context,
                TMDbDatabase::class.java,
                "TMDb.db",
            ).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: TMDbDatabase): MovieDao = db.movieDao

    @Provides
    @Singleton
    fun provideTVShowDao(db: TMDbDatabase): TVShowDao = db.tvShowDao
}
