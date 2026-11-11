package com.sample.tmdb.data.di

import android.content.Context
import androidx.room.Room
import com.sample.tmdb.data.source.local.MIGRATION_1_2
import com.sample.tmdb.data.source.local.MIGRATION_2_3
import com.sample.tmdb.data.source.local.MIGRATION_3_4
import com.sample.tmdb.data.source.local.MIGRATION_4_5
import com.sample.tmdb.data.source.local.MIGRATION_5_6
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.data.source.local.MovieDetailDao
import com.sample.tmdb.data.source.local.MovieFeedDao
import com.sample.tmdb.data.source.local.MoviePagingDao
import com.sample.tmdb.data.source.local.MovieRemoteKeyDao
import com.sample.tmdb.data.source.local.PersonDao
import com.sample.tmdb.data.source.local.PersonFeedDao
import com.sample.tmdb.data.source.local.PersonPagingDao
import com.sample.tmdb.data.source.local.PersonRemoteKeyDao
import com.sample.tmdb.data.source.local.SearchMoviePagingDao
import com.sample.tmdb.data.source.local.SearchMovieRemoteKeyDao
import com.sample.tmdb.data.source.local.SearchTVShowPagingDao
import com.sample.tmdb.data.source.local.SearchTVShowRemoteKeyDao
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.data.source.local.TVShowDetailDao
import com.sample.tmdb.data.source.local.TVShowFeedDao
import com.sample.tmdb.data.source.local.TVShowPagingDao
import com.sample.tmdb.data.source.local.TVShowRemoteKeyDao
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
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6).build()

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

    @Provides
    @Singleton
    fun providePersonDao(db: TMDbDatabase): PersonDao = db.personDao

    @Provides
    @Singleton
    fun provideMovieDetailDao(db: TMDbDatabase): MovieDetailDao = db.movieDetailDao

    @Provides
    @Singleton
    fun provideTVShowDetailDao(db: TMDbDatabase): TVShowDetailDao = db.tvShowDetailDao

    @Provides
    @Singleton
    fun provideMoviePagingDao(db: TMDbDatabase): MoviePagingDao = db.moviePagingDao

    @Provides
    @Singleton
    fun provideTVShowPagingDao(db: TMDbDatabase): TVShowPagingDao = db.tvShowPagingDao

    @Provides
    @Singleton
    fun providePersonPagingDao(db: TMDbDatabase): PersonPagingDao = db.personPagingDao

    @Provides
    @Singleton
    fun provideSearchMoviePagingDao(db: TMDbDatabase): SearchMoviePagingDao = db.searchMoviesPagingDao

    @Provides
    @Singleton
    fun provideSearchTVShowPagingDao(db: TMDbDatabase): SearchTVShowPagingDao = db.searchTVShowPagingDao

    @Provides
    @Singleton
    fun provideMovieRemoteKeyDao(db: TMDbDatabase): MovieRemoteKeyDao = db.movieRemoteKeyDao

    @Provides
    @Singleton
    fun provideTVShowRemoteKeyDao(db: TMDbDatabase): TVShowRemoteKeyDao = db.tvShowRemoteKeyDao

    @Provides
    @Singleton
    fun providePersonRemoteKeyDao(db: TMDbDatabase): PersonRemoteKeyDao = db.personRemoteKeyDao

    @Provides
    @Singleton
    fun provideSearchMovieRemoteKeyDao(db: TMDbDatabase): SearchMovieRemoteKeyDao = db.searchMovieRemoteKeyDao

    @Provides
    @Singleton
    fun provideSearchTVShowRemoteKeyDao(db: TMDbDatabase): SearchTVShowRemoteKeyDao = db.searchTVShowRemoteKeyDao
}
