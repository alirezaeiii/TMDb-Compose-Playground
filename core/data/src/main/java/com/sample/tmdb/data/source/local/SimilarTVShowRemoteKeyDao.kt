package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SimilarTVShowRemoteKeyEntity

@Dao
interface SimilarTVShowRemoteKeyDao {

    @Query("SELECT * FROM SimilarTVShowRemoteKeys WHERE id = :tvShowId")
    suspend fun getRemoteKey(tvShowId: Int): SimilarTVShowRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(key: SimilarTVShowRemoteKeyEntity)

    @Query("DELETE FROM SimilarTVShowRemoteKeys WHERE id = :tvShowId")
    suspend fun deleteRemoteKey(tvShowId: Int)
}
