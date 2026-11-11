package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SearchTVShowRemoteKeyEntity

@Dao
interface SearchTVShowRemoteKeyDao {

    @Query(" SELECT * FROM SearchTVShowRemoteKeys WHERE queryStr = :query")
    suspend fun getRemoteKey(query: String): SearchTVShowRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(key: SearchTVShowRemoteKeyEntity)

    @Query("DELETE FROM SearchMovieRemoteKeys WHERE queryStr = :query")
    suspend fun deleteRemoteKey(query: String)
}
