package com.sample.tmdb.common.model

import com.sample.tmdb.navigation.TrendingPerson

data class People(val result: List<TrendingPerson>, val type: TYPE = TYPE.TRENDING)

enum class TYPE { TRENDING, CREDIT }

fun List<Credit>.asPersonModel(type: TYPE = TYPE.CREDIT): People = People(
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
