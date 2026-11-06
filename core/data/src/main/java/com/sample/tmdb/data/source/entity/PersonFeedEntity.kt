package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.Gender
import com.sample.tmdb.domain.model.TrendingPerson

@Entity(tableName = "PersonFeed", primaryKeys = ["id"])
class PersonFeedEntity(
    val id: Int,
    val name: String,
    val subtitle: String,
    val gender: Gender,
    val profileUrl: String?,
    val itemOrder: Int = 0,
)

fun List<PersonFeedEntity>.asDomainModel() = map(PersonFeedEntity::asDomainModel)

fun TrendingPerson.asFeedDatabaseModel(order: Int): PersonFeedEntity = PersonFeedEntity(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = gender,
    profileUrl = profileUrl,
    itemOrder = order,
)

fun PersonFeedEntity.asDomainModel(): TrendingPerson = TrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = gender,
    profileUrl = profileUrl,
)
