package com.sample.tmdb.common.model

import com.sample.tmdb.common.utils.Gender

data class People(
    val result: List<TrendingPerson>,
    val type: TYPE = TYPE.TRENDING
)

data class TrendingPerson(
    val id: Int,
    val name: String,
    val subtitle: String,
    val gender: Gender,
    val profileUrl: String?,
)

enum class TYPE { TRENDING, CREDIT }

fun List<Credit>.asPersonModel(
    type: TYPE = TYPE.CREDIT
): People = People(
    result = map(Credit::asPersonModel),
    type = type
)

private fun Credit.asPersonModel(): TrendingPerson = TrendingPerson(
    subtitle = role,
    name = name,
    profileUrl = profileUrl,
    gender = gender,
    id = id,
)