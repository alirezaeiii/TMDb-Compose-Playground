package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sample.tmdb.data.source.entity.TVShowFeedEntity
import com.sample.tmdb.data.source.entity.TVShowGenreEntity

@Dao
interface TVShowFeedDao {
    @Query("SELECT * FROM TVShowFeed WHERE genres = :genresKey ORDER BY itemOrder ASC")
    suspend fun getFeed(genresKey: String = ""): List<TVShowFeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feeds: List<TVShowFeedEntity>)

    @Query("DELETE FROM TVShowFeed WHERE genres = :genresKey")
    suspend fun deleteByGenres(genresKey: String = "")

    @Query("DELETE FROM TVShowFeed")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(genresKey: String = "", feeds: List<TVShowFeedEntity>) {
        deleteByGenres(genresKey)
        insertAll(feeds)
    }

    @Query("SELECT * FROM TVShowGenres")
    suspend fun getGenres(): List<TVShowGenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<TVShowGenreEntity>)

    @Query("DELETE FROM TVShowGenres")
    suspend fun deleteAllGenres()

    @Transaction
    suspend fun deleteAndInsertGenres(genres: List<TVShowGenreEntity>) {
        deleteAllGenres()
        insertGenres(genres)
    }
}
