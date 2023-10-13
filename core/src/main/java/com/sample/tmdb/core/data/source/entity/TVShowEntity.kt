package com.sample.tmdb.core.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.tmdb.core.domain.model.TVShow

@Entity(tableName = "TVShows")
class TVShowEntity(
    @PrimaryKey val id: Int,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int
)

fun List<TVShowEntity>.asDomainModel(): List<TVShow> = map {
    TVShow(
        id = it.id,
        overview = it.overview,
        releaseDate = it.releaseDate,
        posterUrl = it.posterUrl,
        backdropUrl = it.backdropUrl,
        name = it.name,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount
    )
}