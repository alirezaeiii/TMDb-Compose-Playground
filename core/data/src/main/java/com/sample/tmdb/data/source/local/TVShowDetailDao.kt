package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.TVShowDetailEntity

@Dao
interface TVShowDetailDao {
    @Query("SELECT * FROM TVShowDetail WHERE id = :id")
    suspend fun getTVShowDetail(id: Int): TVShowDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShowDetailEntity: TVShowDetailEntity)
}
