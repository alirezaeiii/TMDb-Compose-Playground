package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.Gender
import com.sample.tmdb.common.model.TrendingPersonItem
import com.sample.tmdb.navigation.model.Gender as NavGender
import com.sample.tmdb.navigation.model.TrendingPerson as NavTrendingPerson

data class TrendingPerson(
    override val id: Int,
    override val name: String,
    override val subtitle: String,
    override val gender: Gender,
    override val profileUrl: String?,
    override val knownFor: List<Media>? = null,
) : TrendingPersonItem

fun TrendingPerson.asNavPerson(): NavTrendingPerson = NavTrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = if (gender == Gender.MALE) NavGender.MALE else NavGender.FEMALE,
    profileUrl = profileUrl,
    knownFor = knownFor?.asNavMedia(),
)

fun List<TrendingPerson>.asNavPerson(): List<NavTrendingPerson> = map(TrendingPerson::asNavPerson)
