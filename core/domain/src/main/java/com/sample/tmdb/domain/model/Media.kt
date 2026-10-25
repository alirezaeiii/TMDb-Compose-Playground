package com.sample.tmdb.domain.model

import com.sample.tmdb.navigation.model.Media as NavMedia
import com.sample.tmdb.navigation.model.MediaType

interface Media {
    val id: Int
    val posterUrl: String?
    val mediaType: MediaType
    val title: String
}

data class MovieMedia(
    override val id: Int,
    override val posterUrl: String?,
    override val mediaType: MediaType = MediaType.MOVIE,
    override val title: String,
) : Media

data class TVShowMedia(
    override val id: Int,
    override val posterUrl: String?,
    override val mediaType: MediaType = MediaType.TV_SHOW,
    override val title: String,
) : Media

fun Media.asNavMedia(): NavMedia = NavMedia(
    id = id,
    posterUrl = posterUrl,
    mediaType = mediaType,
    title = title,
)

fun List<Media>.asNavMedia(): List<NavMedia> = map(Media::asNavMedia)
