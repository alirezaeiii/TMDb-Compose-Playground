package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.PersonSearchRemoteKeyEntity

@Dao
interface PersonSearchRemoteKeyDao {

    @Query(" SELECT * FROM PersonSearchRemoteKeys WHERE queryStr = :query")
    suspend fun getRemoteKey(query: String): PersonSearchRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(key: List<PersonSearchRemoteKeyEntity>)

    @Query("DELETE FROM PersonSearchRemoteKeys WHERE queryStr = :query")
    suspend fun deleteRemoteKey(query: String)
}
