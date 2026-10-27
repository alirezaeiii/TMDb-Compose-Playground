package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.MediaItem
import com.sample.tmdb.common.model.MediaType
import com.sample.tmdb.navigation.model.Media as NavMedia
import com.sample.tmdb.navigation.model.MediaType as NavMediaType

data class Media(
    override val id: Int,
    override val posterUrl: String?,
    override val mediaType: MediaType,
    override val title: String,
) : MediaItem

fun Media.asNavMedia(): NavMedia = NavMedia(
    id = id,
    posterUrl = posterUrl,
    mediaType = if (mediaType == MediaType.MOVIE) NavMediaType.MOVIE else NavMediaType.TV_SHOW,
    title = title,
)

fun List<Media>.asNavMedia(): List<NavMedia> = map(Media::asNavMedia)

fun NavMedia.asDomainModel(): Media = Media(
    id = id,
    posterUrl = posterUrl,
    mediaType = if (mediaType == NavMediaType.MOVIE) MediaType.MOVIE else MediaType.TV_SHOW,
    title = title,
)

fun List<NavMedia>.asDomainModel() = map(NavMedia::asDomainModel)
