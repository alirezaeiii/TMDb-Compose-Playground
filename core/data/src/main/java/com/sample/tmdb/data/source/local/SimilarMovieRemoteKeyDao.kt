package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SimilarMovieRemoteKeyEntity

@Dao
interface SimilarMovieRemoteKeyDao {

    @Query("SELECT * FROM SimilarMovieRemoteKeys WHERE id = :movieId")
    suspend fun getRemoteKey(movieId: Int): SimilarMovieRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(key: SimilarMovieRemoteKeyEntity)

    @Query("DELETE FROM SimilarMovieRemoteKeys WHERE id = :movieId")
    suspend fun deleteRemoteKey(movieId: Int)
}
