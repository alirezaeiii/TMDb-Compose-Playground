package com.sample.tmdb.common.model

interface TrendingPersonItem {
    val id: Int
    val name: String
    val subtitle: String
    val gender: Gender
    val profileUrl: String?
    val knownFor: List<MediaItem>?
}
