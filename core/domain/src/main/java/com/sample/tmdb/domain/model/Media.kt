package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.MediaItem
import com.sample.tmdb.common.model.MediaType
import com.sample.tmdb.navigation.model.Media as NavMedia
import com.sample.tmdb.navigation.model.MediaType as NavMediaType

interface Media : MediaItem {
    override val id: Int
    override val posterUrl: String?
    override val mediaType: MediaType
    override val title: String
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
    mediaType = if (mediaType == MediaType.MOVIE) NavMediaType.MOVIE else NavMediaType.TV_SHOW,
    title = title,
)

fun List<Media>.asNavMedia(): List<NavMedia> = map(Media::asNavMedia)
