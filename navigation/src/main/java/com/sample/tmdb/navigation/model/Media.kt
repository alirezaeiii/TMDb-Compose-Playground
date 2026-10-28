package com.sample.tmdb.navigation.model

import com.sample.tmdb.domain.model.Media as DomainMedia
import com.sample.tmdb.domain.model.MediaType as DomainMediaType
import kotlinx.serialization.Serializable

@Serializable
data class Media(val id: Int, val posterUrl: String?, val mediaType: MediaType, val title: String)

enum class MediaType {
    MOVIE,
    TV_SHOW,
}

fun DomainMedia.asNavMedia(): Media = Media(
    id = id,
    posterUrl = posterUrl,
    mediaType = if (mediaType == DomainMediaType.MOVIE) MediaType.MOVIE else MediaType.TV_SHOW,
    title = title,
)

fun List<DomainMedia>.asNavMedia(): List<Media> = map(DomainMedia::asNavMedia)

fun Media.asDomainModel(): DomainMedia = DomainMedia(
    id = id,
    posterUrl = posterUrl,
    mediaType = if (mediaType == MediaType.MOVIE) DomainMediaType.MOVIE else DomainMediaType.TV_SHOW,
    title = title,
)

fun List<Media>.asDomainModel() = map(Media::asDomainModel)
