package com.sample.tmdb.domain.model

interface Credit {
    val id: Int
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}

class Cast(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int,
) : Credit

class Crew(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int,
) : Credit
