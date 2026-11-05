package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow

@Entity(tableName = "TVShowFeed", primaryKeys = ["sortType", "id"])
class TVShowFeedEntity(
    val id: Int,
    val sortType: SortType,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int,
    val itemOrder: Int = 0,
)

fun List<TVShowFeedEntity>.asDomainModel() = map(TVShowFeedEntity::asDomainModel)

fun TVShow.asFeedDatabaseModel(sortType: SortType, order: Int): TVShowFeedEntity = TVShowFeedEntity(
    id = id,
    sortType = sortType,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
    itemOrder = order,
)

fun TVShowFeedEntity.asDomainModel(): TVShow = TVShow(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
