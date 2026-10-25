package com.sample.tmdb.navigation.model

import kotlinx.serialization.Serializable

@Serializable
data class Media(val id: Int, val posterUrl: String?, val mediaType: MediaType, val title: String)
