package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SimilarMoviePagingEntity

@Dao
interface SimilarMoviePagingDao {

    @Query("SELECT * FROM SimilarMoviePaging WHERE movieId = :movieId ORDER BY itemOrder ASC")
    fun getPagingMovies(movieId: Int): PagingSource<Int, SimilarMoviePagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<SimilarMoviePagingEntity>)

    @Query("DELETE FROM SimilarMoviePaging WHERE movieId = :movieId")
    suspend fun deleteMovies(movieId: Int)
}
