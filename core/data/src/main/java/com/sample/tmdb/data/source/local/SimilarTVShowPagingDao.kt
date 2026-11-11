package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SimilarTVShowPagingEntity

@Dao
interface SimilarTVShowPagingDao {

    @Query("SELECT * FROM SimilarTVShowPaging WHERE tvShowId = :tvShowId ORDER BY itemOrder ASC")
    fun getPagingTVShows(tvShowId: Int): PagingSource<Int, SimilarTVShowPagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShows: List<SimilarTVShowPagingEntity>)

    @Query("DELETE FROM SimilarTVShowPaging WHERE tvShowId = :tvShowId")
    suspend fun deleteMovies(tvShowId: Int)
}
