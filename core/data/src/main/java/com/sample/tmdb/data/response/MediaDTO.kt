package com.sample.tmdb.data.response

import com.sample.tmdb.common.utils.Constants.BASE_WIDTH_342_PATH
import com.sample.tmdb.common.utils.Constants.ID
import com.sample.tmdb.common.utils.Constants.NAME
import com.sample.tmdb.common.utils.Constants.POSTER_PATH
import com.sample.tmdb.common.utils.Constants.TITLE
import com.sample.tmdb.domain.model.Media
import com.sample.tmdb.domain.model.MovieMedia
import com.sample.tmdb.domain.model.TVShowMedia
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaDTO(
    @Json(name = ID)
    val id: Int,
    @Json(name = POSTER_PATH)
    val posterPath: String?,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = TITLE)
    val title: String? = null,
    @Json(name = NAME)
    val name: String? = null,
)

fun List<MediaDTO>.asDomainModel(): List<Media> = map { dto ->
    val formattedPoster = dto.posterPath?.let { posterPath ->
        String.format(BASE_WIDTH_342_PATH, posterPath)
    }
    if (dto.mediaType == "movie") {
        MovieMedia(
            id = dto.id,
            posterUrl = formattedPoster,
            title = dto.title.orEmpty(),
        )
    } else {
        TVShowMedia(
            id = dto.id,
            posterUrl = formattedPoster,
            title = dto.name.orEmpty(),
        )
    }
}
