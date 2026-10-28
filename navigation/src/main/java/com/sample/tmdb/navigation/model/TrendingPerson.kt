package com.sample.tmdb.navigation.model

import com.sample.tmdb.domain.model.Gender as DomainGender
import com.sample.tmdb.domain.model.TrendingPerson as DomainTrendingPerson
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

enum class Gender { MALE, FEMALE }

fun DomainTrendingPerson.asNavPerson(): TrendingPerson = TrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = if (gender == DomainGender.MALE) Gender.MALE else Gender.FEMALE,
    profileUrl = profileUrl,
    knownFor = knownFor?.asNavMedia(),
)

fun List<DomainTrendingPerson>.asNavPerson(): List<TrendingPerson> = map(DomainTrendingPerson::asNavPerson)
