package com.sample.tmdb.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.tmdb.data.source.entity.MovieEntity
import com.sample.tmdb.data.source.entity.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class], version = 1, exportSchema = false)
abstract class TMDbDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val tvShowDao: TVShowDao
}