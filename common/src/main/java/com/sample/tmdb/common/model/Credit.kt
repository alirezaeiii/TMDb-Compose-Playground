package com.sample.tmdb.common.model

interface Credit {
    val id: Int
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}
