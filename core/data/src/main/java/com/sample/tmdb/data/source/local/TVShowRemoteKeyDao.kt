package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.TVShowRemoteKeyEntity
import com.sample.tmdb.domain.model.SortType

@Dao
interface TVShowRemoteKeyDao {

    @Query("SELECT * FROM TVShowRemoteKeys WHERE sortType = :sortType AND genres = :genresKey")
    suspend fun getRemoteKey(sortType: SortType, genresKey: String = ""): TVShowRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKey: TVShowRemoteKeyEntity)

    @Query("DELETE FROM TVShowRemoteKeys WHERE sortType = :sortType AND genres = :genresKey")
    suspend fun deleteRemoteKey(sortType: SortType, genresKey: String = "")
}
