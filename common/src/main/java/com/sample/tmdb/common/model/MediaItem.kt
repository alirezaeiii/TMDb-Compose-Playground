package com.sample.tmdb.common.model

import com.sample.tmdb.navigation.model.MediaType

interface MediaItem {
    val id: Int
    val posterUrl: String?
    val mediaType: MediaType
    val title: String
}
