package com.sample.tmdb.data.response

import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.utils.Constants
import com.squareup.moshi.Json

class PersonResponse(
    @Json(name = "birthday")
    val birthDay: String?,
    @Json(name = "deathday")
    val deathDay: String?,
    val id: Int,
    val name: String,
    val biography: String,
    @Json(name = "place_of_birth")
    val placeOfBirth: String?,
    @Json(name = "profile_path")
    val profilePath: String?,
)

fun PersonResponse.asDomainModel(): Person =
    Person(
        birthDay,
        deathDay,
        id,
        name,
        biography,
        placeOfBirth,
        profilePath?.let { profilePath ->
            String.format(
                Constants.BASE_WIDTH_342_PATH,
                profilePath
            )
        })