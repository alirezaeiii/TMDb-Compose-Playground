package com.sample.tmdb.core.domain.model

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.core.data.source.entity.MovieEntity
import com.sample.tmdb.core.data.source.entity.TVShowEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int
) : TMDbItem

@Parcelize
data class TVShow(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int
) : TMDbItem

fun Movie.asDatabaseModel(): MovieEntity = MovieEntity(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun TVShow.asDatabaseModel(): TVShowEntity = TVShowEntity(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount
)