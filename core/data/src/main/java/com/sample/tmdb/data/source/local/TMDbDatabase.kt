package com.sample.tmdb.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sample.tmdb.data.source.entity.MovieDetailEntity
import com.sample.tmdb.data.source.entity.MovieEntity
import com.sample.tmdb.data.source.entity.MovieFeedEntity
import com.sample.tmdb.data.source.entity.PersonEntity
import com.sample.tmdb.data.source.entity.PersonFeedEntity
import com.sample.tmdb.data.source.entity.TVShowDetailEntity
import com.sample.tmdb.data.source.entity.TVShowEntity
import com.sample.tmdb.data.source.entity.TVShowFeedEntity

@Database(
    entities = [
        MovieEntity::class,
        TVShowEntity::class,
        MovieFeedEntity::class,
        TVShowFeedEntity::class,
        PersonFeedEntity::class,
        PersonEntity::class,
        MovieDetailEntity::class,
        TVShowDetailEntity::class,
    ],
    version = 5,
    exportSchema = true,
)
@TypeConverters(FeedTypeConverters::class, DetailTypeConverters::class)
abstract class TMDbDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val tvShowDao: TVShowDao
    abstract val movieFeedDao: MovieFeedDao
    abstract val tvShowFeedDao: TVShowFeedDao
    abstract val personFeedDao: PersonFeedDao
    abstract val personDao: PersonDao
    abstract val movieDetailDao: MovieDetailDao
    abstract val tvShowDetailDao: TVShowDetailDao
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

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `PersonFeed` (
                `id` INTEGER NOT NULL,
                `gender` TEXT NOT NULL,
                `itemOrder` INTEGER NOT NULL,
                `name` TEXT NOT NULL,
                `profileUrl` TEXT,
                `subtitle` TEXT NOT NULL,
                PRIMARY KEY(`id`)
            )
            """.trimIndent(),
        )
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `Person` (
                `biography` TEXT NOT NULL,
                `birthDay` TEXT,
                `deathDay` TEXT,
                `id` INTEGER NOT NULL,
                `name` TEXT NOT NULL,
                `placeOfBirth` TEXT,
                `profilePath` TEXT,
                PRIMARY KEY(`id`)
            )
            """.trimIndent(),
        )
    }
}

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `MovieDetail` (
                `id` INTEGER NOT NULL,
                `backdropPath` TEXT,
                `genres` TEXT NOT NULL,
                `homepage` TEXT,
                `originalLanguage` TEXT NOT NULL,
                `originalTitle` TEXT NOT NULL,
                `overview` TEXT NOT NULL,
                `popularity` REAL NOT NULL,
                `posterPath` TEXT,
                `releaseDate` TEXT,
                `spokenLanguages` TEXT NOT NULL,
                `status` TEXT NOT NULL,
                `tagline` TEXT NOT NULL,
                `title` TEXT NOT NULL,
                `voteAverage` REAL NOT NULL,
                `voteCount` INTEGER NOT NULL,
                `cast` TEXT NOT NULL,
                `crew` TEXT NOT NULL,
                `images` TEXT NOT NULL,
                `similarItems` TEXT NOT NULL,
                PRIMARY KEY(`id`)
            )
            """.trimIndent(),
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `TVShowDetail` (
                `id` INTEGER NOT NULL,
                `backdropPath` TEXT,
                `genres` TEXT NOT NULL,
                `homepage` TEXT,
                `originalLanguage` TEXT NOT NULL,
                `originalTitle` TEXT NOT NULL,
                `overview` TEXT NOT NULL,
                `popularity` REAL NOT NULL,
                `posterPath` TEXT,
                `releaseDate` TEXT,
                `spokenLanguages` TEXT NOT NULL,
                `status` TEXT NOT NULL,
                `tagline` TEXT NOT NULL,
                `title` TEXT NOT NULL,
                `voteAverage` REAL NOT NULL,
                `voteCount` INTEGER NOT NULL,
                `cast` TEXT NOT NULL,
                `crew` TEXT NOT NULL,
                `images` TEXT NOT NULL,
                `similarItems` TEXT NOT NULL,
                PRIMARY KEY(`id`)
            )
            """.trimIndent(),
        )
    }
}
