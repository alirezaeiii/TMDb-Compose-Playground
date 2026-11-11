package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.PersonSearchPagingEntity

@Dao
interface PersonSearchPagingDao {

    @Query("SELECT * FROM PersonSearchPaging WHERE queryStr = :query ORDER BY itemOrder ASC")
    fun getPersons(query: String): PagingSource<Int, PersonSearchPagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(persons: List<PersonSearchPagingEntity>)

    @Query("DELETE FROM PersonSearchPaging WHERE queryStr = :query")
    suspend fun deleteAll(query: String)
}
