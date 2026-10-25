package com.sample.tmdb.common.model

import com.sample.tmdb.navigation.Gender

interface Credit {
    val id: Int
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}
