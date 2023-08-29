package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.TVShowEntity

@Dao
interface TVShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(tvShowEntity: TVShowEntity)

    @Query("DELETE FROM TVShows WHERE id = :id")
    suspend fun deleteBookmark(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM TVShows WHERE id = :id)")
    suspend fun isBookmarked(id: Int): Boolean

    @Query("SELECT * FROM TVShows ORDER BY releaseDate DESC")
    suspend fun getBookmarks(): List<TVShowEntity>
}