package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.tmdb.domain.model.Genre

@Entity(tableName = "MovieGenres")
class MovieGenreEntity(@PrimaryKey val id: Int, val name: String?)

fun List<MovieGenreEntity>.asDomainModel(): List<Genre> = map { Genre(it.id, it.name) }

fun List<Genre>.asMovieGenreDatabaseModel(): List<MovieGenreEntity> = map {
    MovieGenreEntity(id = it.id, name = it.name)
}
