package com.sample.tmdb.common.model

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
