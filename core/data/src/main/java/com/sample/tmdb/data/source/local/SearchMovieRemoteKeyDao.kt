package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SearchMovieRemoteKeyEntity

@Dao
interface SearchMovieRemoteKeyDao {

    @Query(" SELECT * FROM SearchMovieRemoteKeys WHERE queryStr = :query")
    suspend fun getRemoteKey(query: String): SearchMovieRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(key: SearchMovieRemoteKeyEntity)

    @Query("DELETE FROM SearchMovieRemoteKeys WHERE queryStr = :query")
    suspend fun deleteRemoteKey(query: String)
}
