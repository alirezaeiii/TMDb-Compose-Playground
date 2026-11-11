package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow

@Entity(tableName = "TVShowPaging", primaryKeys = ["sortType", "id"])
class TVShowPagingEntity(
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

fun List<TVShowPagingEntity>.asDomainModel() = map(TVShowPagingEntity::asDomainModel)

fun TVShowPagingEntity.asDomainModel(): TVShow = TVShow(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
