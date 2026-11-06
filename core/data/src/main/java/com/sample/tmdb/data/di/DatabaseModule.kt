package com.sample.tmdb.data.di

import android.content.Context
import androidx.room.Room
import com.sample.tmdb.data.source.local.MIGRATION_1_2
import com.sample.tmdb.data.source.local.MIGRATION_2_3
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.data.source.local.MovieFeedDao
import com.sample.tmdb.data.source.local.PersonFeedDao
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.data.source.local.TVShowFeedDao
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
    fun provideTMDbDatabase(@ApplicationContext context: Context): TMDbDatabase = Room
        .databaseBuilder(
            context,
            TMDbDatabase::class.java,
            "TMDb.db",
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: TMDbDatabase): MovieDao = db.movieDao

    @Provides
    @Singleton
    fun provideTVShowDao(db: TMDbDatabase): TVShowDao = db.tvShowDao

    @Provides
    @Singleton
    fun provideMovieFeedDao(db: TMDbDatabase): MovieFeedDao = db.movieFeedDao

    @Provides
    @Singleton
    fun provideTVShowFeedDao(db: TMDbDatabase): TVShowFeedDao = db.tvShowFeedDao

    @Provides
    @Singleton
    fun providePersonFeedDao(db: TMDbDatabase): PersonFeedDao = db.personFeedDao
}
