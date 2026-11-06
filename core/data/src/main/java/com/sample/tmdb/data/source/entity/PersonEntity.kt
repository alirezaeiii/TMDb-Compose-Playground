package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.Person

@Entity(tableName = "Person", primaryKeys = ["id"])
class PersonEntity(
    val birthDay: String?,
    val deathDay: String?,
    val id: Int,
    val name: String,
    val biography: String,
    val placeOfBirth: String?,
    val profilePath: String?,
)

fun PersonEntity.asDomainModel(): Person = Person(
    birthDay = birthDay,
    deathDay = deathDay,
    id = id,
    name = name,
    biography = biography,
    placeOfBirth = placeOfBirth,
    profilePath = profilePath,
)

fun Person.asDatabaseModel(): PersonEntity = PersonEntity(
    birthDay = birthDay,
    deathDay = deathDay,
    id = id,
    name = name,
    biography = biography,
    placeOfBirth = placeOfBirth,
    profilePath = profilePath,
)
