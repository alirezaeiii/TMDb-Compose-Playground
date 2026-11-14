package com.sample.tmdb.data.response

import com.sample.tmdb.data.utils.Constants.ID
import com.sample.tmdb.data.utils.Constants.NAME
import com.sample.tmdb.domain.model.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresDTO(
    @Json(name = "genres")
    val genres: List<GenreDTO>,
)

@JsonClass(generateAdapter = true)
data class GenreDTO(
    @Json(name = ID)
    val id: Int,
    @Json(name = NAME)
    val name: String,
)

fun GenresDTO.asDomainModel(): List<Genre> = genres.map { Genre(it.id, it.name) }
