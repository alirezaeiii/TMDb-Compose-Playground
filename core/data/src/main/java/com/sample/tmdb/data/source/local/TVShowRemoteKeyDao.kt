package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.TVShowRemoteKeyEntity
import com.sample.tmdb.domain.model.SortType

@Dao
interface TVShowRemoteKeyDao {

    @Query("SELECT * FROM TVShowRemoteKeys WHERE sortType = :sortType")
    suspend fun getRemoteKey(sortType: SortType): TVShowRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKey: TVShowRemoteKeyEntity)

    @Query("DELETE FROM TVShowRemoteKeys WHERE sortType = :sortType")
    suspend fun deleteRemoteKey(sortType: SortType)
}
