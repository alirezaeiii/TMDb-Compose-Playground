package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.MoviePagingEntity
import com.sample.tmdb.domain.model.SortType

@Dao
interface MoviePagingDao {

    @Query("SELECT * FROM MoviePaging WHERE sortType = :sortType AND genres = :genresKey ORDER BY itemOrder ASC")
    fun getPagingMovies(sortType: SortType, genresKey: String = ""): PagingSource<Int, MoviePagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MoviePagingEntity>)

    @Query("DELETE FROM MoviePaging WHERE sortType = :sortType AND genres = :genresKey")
    suspend fun deleteBySortType(sortType: SortType, genresKey: String = "")
}
