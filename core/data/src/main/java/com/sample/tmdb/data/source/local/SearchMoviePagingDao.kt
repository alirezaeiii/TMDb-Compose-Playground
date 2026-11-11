package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.SearchMoviePagingEntity

@Dao
interface SearchMoviePagingDao {

    @Query("SELECT * FROM SearchMoviePaging WHERE queryStr = :query ORDER BY itemOrder ASC")
    fun getPagingMovies(query: String): PagingSource<Int, SearchMoviePagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<SearchMoviePagingEntity>)

    @Query("DELETE FROM SearchMoviePaging WHERE queryStr = :query")
    suspend fun deleteMovies(query: String)
}
