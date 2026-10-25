package com.sample.tmdb.common.model

import com.sample.tmdb.navigation.model.Gender

interface Credit {
    val id: Int
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}
