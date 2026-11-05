package com.sample.tmdb.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sample.tmdb.data.source.entity.MovieEntity
import com.sample.tmdb.data.source.entity.MovieFeedEntity
import com.sample.tmdb.data.source.entity.TVShowEntity
import com.sample.tmdb.data.source.entity.TVShowFeedEntity

@Database(
    entities = [
        MovieEntity::class,
        TVShowEntity::class,
        MovieFeedEntity::class,
        TVShowFeedEntity::class,
    ],
    version = 2,
    exportSchema = true,
)
@TypeConverters(FeedTypeConverters::class)
abstract class TMDbDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val tvShowDao: TVShowDao
    abstract val movieFeedDao: MovieFeedDao
    abstract val tvShowFeedDao: TVShowFeedDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `MovieFeed` (
                `backdropUrl` TEXT,
                `id` INTEGER NOT NULL,
                `itemOrder` INTEGER NOT NULL,
                `name` TEXT NOT NULL,
                `overview` TEXT NOT NULL,
                `posterUrl` TEXT,
                `releaseDate` TEXT,
                `sortType` TEXT NOT NULL,
                `voteAverage` REAL NOT NULL,
                `voteCount` INTEGER NOT NULL,
                PRIMARY KEY(`sortType`, `id`)
            )
            """.trimIndent(),
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `TVShowFeed` (
                `backdropUrl` TEXT,
                `id` INTEGER NOT NULL,
                `itemOrder` INTEGER NOT NULL,
                `name` TEXT NOT NULL,
                `overview` TEXT NOT NULL,
                `posterUrl` TEXT,
                `releaseDate` TEXT,
                `sortType` TEXT NOT NULL,
                `voteAverage` REAL NOT NULL,
                `voteCount` INTEGER NOT NULL,
                PRIMARY KEY(`sortType`, `id`)
            )
            """.trimIndent(),
        )
    }
}
