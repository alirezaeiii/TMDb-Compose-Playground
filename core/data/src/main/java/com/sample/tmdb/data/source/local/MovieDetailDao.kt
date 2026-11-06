package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.MovieDetailEntity

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM MovieDetail WHERE id = :id")
    suspend fun getMovieDetail(id: Int): MovieDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetailEntity: MovieDetailEntity)
}
