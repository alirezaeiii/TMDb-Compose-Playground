package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.TVShow

@Entity(tableName = "SimilarTVShowPaging", primaryKeys = ["tvShowId", "id"])
class SimilarTVShowPagingEntity(
    val tvShowId: Int,
    val id: Int,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int,
    val itemOrder: Int = 0,
)

fun List<SimilarTVShowPagingEntity>.asDomainModel() = map(SimilarTVShowPagingEntity::asDomainModel)

fun SimilarTVShowPagingEntity.asDomainModel(): TVShow = TVShow(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
