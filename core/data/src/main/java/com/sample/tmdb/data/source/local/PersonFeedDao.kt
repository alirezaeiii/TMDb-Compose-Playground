package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sample.tmdb.data.source.entity.PersonFeedEntity

@Dao
interface PersonFeedDao {
    @Query("SELECT * FROM PersonFeed ORDER BY itemOrder ASC")
    suspend fun getFeed(): List<PersonFeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feeds: List<PersonFeedEntity>)

    @Query("DELETE FROM PersonFeed")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(feeds: List<PersonFeedEntity>) {
        deleteAll()
        insertAll(feeds)
    }
}
