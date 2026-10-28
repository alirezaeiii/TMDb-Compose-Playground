package com.sample.tmdb.domain.model

interface TrendingPersonItem {
    val id: Int
    val name: String
    val subtitle: String
    val gender: Gender
    val profileUrl: String?
    val knownFor: List<MediaItem>?
}

data class TrendingPerson(
    override val id: Int,
    override val name: String,
    override val subtitle: String,
    override val gender: Gender,
    override val profileUrl: String?,
    override val knownFor: List<Media>? = null,
) : TrendingPersonItem
