package com.sample.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.tmdb.data.source.entity.PersonEntity

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person WHERE id = :id")
    suspend fun getPerson(id: Int): PersonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personEntity: PersonEntity)
}
