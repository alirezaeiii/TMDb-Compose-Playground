package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.data.utils.toGender
import com.sample.tmdb.domain.model.TrendingPerson

@Entity(
    tableName = "PersonSearchPaging",
    primaryKeys = ["queryStr", "id"],
)
class PersonSearchPagingEntity(
    val queryStr: String,
    val id: Int,
    val name: String,
    val subtitle: String,
    val gender: Int,
    val profileUrl: String?,
    val itemOrder: Int = 0,
)

fun List<PersonSearchPagingEntity>.asDomainModel() = map(PersonSearchPagingEntity::asDomainModel)

fun PersonSearchPagingEntity.asDomainModel(): TrendingPerson = TrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = gender.toGender(),
    profileUrl = profileUrl,
)
