package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType

@Entity(tableName = "MoviePaging", primaryKeys = ["sortType", "id"])
class MoviePagingEntity(
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

fun List<MoviePagingEntity>.asDomainModel() = map(MoviePagingEntity::asDomainModel)

fun MoviePagingEntity.asDomainModel(): Movie = Movie(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
