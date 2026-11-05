package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType

@Entity(tableName = "MovieFeed", primaryKeys = ["sortType", "id"])
class MovieFeedEntity(
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

fun List<MovieFeedEntity>.asDomainModel() = map(MovieFeedEntity::asDomainModel)

fun Movie.asFeedDatabaseModel(sortType: SortType, order: Int): MovieFeedEntity = MovieFeedEntity(
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

fun MovieFeedEntity.asDomainModel(): Movie = Movie(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
