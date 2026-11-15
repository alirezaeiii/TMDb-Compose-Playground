package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.TVShowPagingEntity
import com.sample.tmdb.domain.model.SortType

@Dao
interface TVShowPagingDao {

    @Query("SELECT * FROM TVShowPaging WHERE sortType = :sortType AND genres = :genresKey ORDER BY itemOrder ASC")
    fun getPagingTVShows(sortType: SortType, genresKey: String = ""): PagingSource<Int, TVShowPagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShows: List<TVShowPagingEntity>)

    @Query("DELETE FROM TVShowPaging WHERE sortType = :sortType AND genres = :genresKey")
    suspend fun deleteBySortType(sortType: SortType, genresKey: String = "")
}
