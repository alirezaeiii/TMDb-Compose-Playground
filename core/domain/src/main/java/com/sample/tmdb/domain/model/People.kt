package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.Credit

data class People(val result: List<TrendingPerson>, val type: PeopleType = PeopleType.TRENDING)

enum class PeopleType { TRENDING, CREDIT }

fun List<Credit>.asPersonModel(type: PeopleType = PeopleType.CREDIT): People = People(
    result = map(Credit::asPersonModel),
    type = type,
)

private fun Credit.asPersonModel(): TrendingPerson = TrendingPerson(
    subtitle = role,
    name = name,
    profileUrl = profileUrl,
    gender = gender,
    id = id,
)
