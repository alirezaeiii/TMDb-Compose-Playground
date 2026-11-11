package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.Movie

@Entity(tableName = "SimilarMoviePaging", primaryKeys = ["movieId", "id"])
class SimilarMoviePagingEntity(
    val movieId: Int,
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

fun List<SimilarMoviePagingEntity>.asDomainModel() = map(SimilarMoviePagingEntity::asDomainModel)

fun SimilarMoviePagingEntity.asDomainModel(): Movie = Movie(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
