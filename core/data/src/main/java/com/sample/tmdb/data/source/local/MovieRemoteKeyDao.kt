package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.MovieRemoteKeyEntity
import com.sample.tmdb.domain.model.SortType

@Dao
interface MovieRemoteKeyDao {

    @Query("SELECT * FROM MovieRemoteKeys WHERE sortType = :sortType AND genres = :genresKey")
    suspend fun getRemoteKey(sortType: SortType, genresKey: String = ""): MovieRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKey: MovieRemoteKeyEntity)

    @Query("DELETE FROM MovieRemoteKeys WHERE sortType = :sortType AND genres = :genresKey")
    suspend fun deleteRemoteKey(sortType: SortType, genresKey: String = "")
}
