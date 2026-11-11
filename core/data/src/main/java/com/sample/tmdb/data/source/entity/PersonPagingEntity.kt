package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.data.utils.toGender
import com.sample.tmdb.domain.model.TrendingPerson

@Entity(tableName = "PersonPaging", primaryKeys = ["id"])
class PersonPagingEntity(
    val id: Int,
    val name: String,
    val subtitle: String,
    val gender: Int,
    val profileUrl: String?,
    val itemOrder: Int = 0,
)

fun List<PersonPagingEntity>.asDomainModel() = map(PersonPagingEntity::asDomainModel)

fun PersonPagingEntity.asDomainModel(): TrendingPerson = TrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = gender.toGender(),
    profileUrl = profileUrl,
)
