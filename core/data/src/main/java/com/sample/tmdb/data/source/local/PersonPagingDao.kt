package com.sample.tmdb.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.PersonPagingEntity

@Dao
interface PersonPagingDao {

    @Query("SELECT * FROM PersonPaging ORDER BY itemOrder ASC")
    fun getPagingPersons(): PagingSource<Int, PersonPagingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(persons: List<PersonPagingEntity>)

    @Query("DELETE FROM PersonPaging")
    suspend fun deleteAll()
}
