package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.Movie

@Entity(
    tableName = "SearchMoviePaging",
    primaryKeys = ["queryStr", "id"],
)
data class SearchMoviePagingEntity(
    val queryStr: String,
    val id: Int,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int,
    val itemOrder: Int,
)

fun List<SearchMoviePagingEntity>.asDomainModel() = map(SearchMoviePagingEntity::asDomainModel)

fun SearchMoviePagingEntity.asDomainModel(): Movie = Movie(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
