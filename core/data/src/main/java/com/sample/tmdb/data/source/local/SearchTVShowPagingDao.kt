package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SearchTVShowPagingEntity

@Dao
interface SearchTVShowPagingDao {

    @Query("SELECT * FROM SearchTVShowPaging WHERE queryStr = :query ORDER BY itemOrder ASC")
    fun getPagingTVShows(query: String): PagingSource<Int, SearchTVShowPagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShows(tvShows: List<SearchTVShowPagingEntity>)

    @Query("DELETE FROM SearchTVShowPaging WHERE queryStr = :query ")
    suspend fun deleteTVShows(query: String)
}
