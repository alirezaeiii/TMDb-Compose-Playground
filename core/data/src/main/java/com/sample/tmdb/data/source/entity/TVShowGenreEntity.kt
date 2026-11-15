package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.tmdb.domain.model.Genre

@Entity(tableName = "TVShowGenres")
class TVShowGenreEntity(@PrimaryKey val id: Int, val name: String?)

fun List<TVShowGenreEntity>.asDomainModel(): List<Genre> = map { Genre(it.id, it.name) }

fun List<Genre>.asTVShowGenreDatabaseModel(): List<TVShowGenreEntity> = map {
    TVShowGenreEntity(id = it.id, name = it.name)
}
