package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sample.tmdb.data.source.entity.TVShowFeedEntity

@Dao
interface TVShowFeedDao {
    @Query("SELECT * FROM TVShowFeed ORDER BY itemOrder ASC")
    suspend fun getFeed(): List<TVShowFeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feeds: List<TVShowFeedEntity>)

    @Query("DELETE FROM TVShowFeed")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(feeds: List<TVShowFeedEntity>) {
        deleteAll()
        insertAll(feeds)
    }
}
