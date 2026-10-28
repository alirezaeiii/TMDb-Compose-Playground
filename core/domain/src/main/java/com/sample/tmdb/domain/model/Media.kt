package com.sample.tmdb.domain.model

interface MediaItem {
    val id: Int
    val posterUrl: String?
    val mediaType: MediaType
    val title: String
}

enum class MediaType {
    MOVIE,
    TV_SHOW,
}

data class Media(
    override val id: Int,
    override val posterUrl: String?,
    override val mediaType: MediaType,
    override val title: String,
) : MediaItem
