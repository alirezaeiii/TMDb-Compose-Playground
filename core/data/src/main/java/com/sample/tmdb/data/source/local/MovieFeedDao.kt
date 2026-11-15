package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sample.tmdb.data.source.entity.MovieFeedEntity
import com.sample.tmdb.data.source.entity.MovieGenreEntity

@Dao
interface MovieFeedDao {
    @Query("SELECT * FROM MovieFeed WHERE genres = :genresKey ORDER BY itemOrder ASC")
    suspend fun getFeed(genresKey: String = ""): List<MovieFeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feeds: List<MovieFeedEntity>)

    @Query("DELETE FROM MovieFeed WHERE genres = :genresKey")
    suspend fun deleteByGenres(genresKey: String = "")

    @Query("DELETE FROM MovieFeed")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(genresKey: String = "", feeds: List<MovieFeedEntity>) {
        deleteByGenres(genresKey)
        insertAll(feeds)
    }

    @Query("SELECT * FROM MovieGenres")
    suspend fun getGenres(): List<MovieGenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<MovieGenreEntity>)

    @Query("DELETE FROM MovieGenres")
    suspend fun deleteAllGenres()

    @Transaction
    suspend fun deleteAndInsertGenres(genres: List<MovieGenreEntity>) {
        deleteAllGenres()
        insertGenres(genres)
    }
}
