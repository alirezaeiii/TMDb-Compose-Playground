package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.model.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
class Cast(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int,
) : Credit

@Parcelize
class Crew(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: String,
) : Credit
