package com.sample.tmdb.common.model

import com.sample.tmdb.navigation.model.Gender

interface TrendingPersonItem {
    val id: Int
    val name: String
    val subtitle: String
    val gender: Gender
    val profileUrl: String?
    val knownFor: List<MediaItem>?
}
