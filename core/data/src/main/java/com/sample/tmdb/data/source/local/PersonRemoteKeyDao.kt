package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.PersonRemoteKeyEntity

@Dao
interface PersonRemoteKeyDao {

    @Query("SELECT * FROM PersonRemoteKeys WHERE id = :id")
    suspend fun getRemoteKey(id: Int): PersonRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<PersonRemoteKeyEntity>)

    @Query("DELETE FROM PersonRemoteKeys")
    suspend fun deleteAllRemoteKeys()
}
