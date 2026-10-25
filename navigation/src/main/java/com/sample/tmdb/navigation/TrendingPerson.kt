package com.sample.tmdb.navigation

import kotlinx.serialization.Serializable

@Serializable
data class TrendingPerson(
    val id: Int,
    val name: String,
    val subtitle: String,
    val gender: Gender,
    val profileUrl: String?,
    val knownFor: List<Media>? = null,
)
