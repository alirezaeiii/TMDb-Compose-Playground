package com.sample.tmdb.data.response

import com.sample.tmdb.common.model.Gender
import com.sample.tmdb.common.utils.Constants.BASE_WIDTH_342_PATH
import com.sample.tmdb.common.utils.Constants.ID
import com.sample.tmdb.common.utils.Constants.NAME
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCast(
    @Json(name = "character")
    val role: String,
    @Json(name = NAME)
    val name: String,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    @Json(name = GENDER)
    val gender: Int,
    @Json(name = ID)
    val id: Int,
)

@JsonClass(generateAdapter = true)
data class NetworkCrew(
    @Json(name = "job")
    val role: String,
    @Json(name = NAME)
    val name: String,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    @Json(name = GENDER)
    val gender: Int,
    @Json(name = ID)
    val id: String,
)

fun List<NetworkCast>.asCastDomainModel() = map(NetworkCast::asCastDomainModel)

fun List<NetworkCrew>.asCrewDomainModel() = map(NetworkCrew::asCrewDomainModel)

private fun NetworkCast.asCastDomainModel(): Cast = Cast(
    role,
    name,
    profilePath?.let { profilePath ->
        String.format(
            BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
    gender.toGender(),
    id,
)

private fun NetworkCrew.asCrewDomainModel(): Crew = Crew(
    role,
    name,
    profilePath?.let { profilePath ->
        String.format(
            BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
    gender.toGender(),
    id,
)

private fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE

private const val PROFILE_PATH = "profile_path"
private const val GENDER = "gender"
