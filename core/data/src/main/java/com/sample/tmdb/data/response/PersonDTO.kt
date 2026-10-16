package com.sample.tmdb.data.response

import com.sample.tmdb.common.utils.Constants
import com.sample.tmdb.common.utils.Constants.ID
import com.sample.tmdb.common.utils.Constants.NAME
import com.sample.tmdb.common.utils.Constants.PROFILE_PATH
import com.sample.tmdb.domain.model.Person
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDTO(
    @Json(name = "birthday")
    val birthDay: String?,
    @Json(name = "deathday")
    val deathDay: String?,
    @Json(name = ID)
    val id: Int,
    @Json(name = NAME)
    val name: String,
    @Json(name = "biography")
    val biography: String,
    @Json(name = "place_of_birth")
    val placeOfBirth: String?,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
)

fun PersonDTO.asDomainModel(): Person = Person(
    birthDay,
    deathDay,
    id,
    name,
    biography,
    placeOfBirth,
    profilePath?.let { profilePath ->
        String.format(
            Constants.BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
)
