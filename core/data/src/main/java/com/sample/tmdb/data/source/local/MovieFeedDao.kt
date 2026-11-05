package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sample.tmdb.data.source.entity.MovieFeedEntity

@Dao
interface MovieFeedDao {
    @Query("SELECT * FROM MovieFeed ORDER BY itemOrder ASC")
    suspend fun getFeed(): List<MovieFeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feeds: List<MovieFeedEntity>)

    @Query("DELETE FROM MovieFeed")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(feeds: List<MovieFeedEntity>) {
        deleteAll()
        insertAll(feeds)
    }
}
