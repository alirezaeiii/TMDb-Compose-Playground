package com.sample.tmdb.data.response

import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.Gender
import com.sample.tmdb.utils.Constants.BASE_WIDTH_342_PATH
import com.squareup.moshi.Json

class NetworkCast(
    @Json(name = "character")
    val role: String,
    val name: String,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    val gender: Int,
    val id: Int
)

class NetworkCrew(
    @Json(name = "job")
    val role: String,
    val name: String,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    val gender: Int,
    val id: String
)

fun List<NetworkCast>.asCastDomainModel(): List<Cast> = map {
    Cast(
        it.role,
        it.name,
        it.profilePath?.let { profilePath ->
            String.format(
                BASE_WIDTH_342_PATH,
                profilePath
            )
        },
        it.gender.toGender(),
        it.id
    )
}

fun List<NetworkCrew>.asCrewDomainModel(): List<Crew> = map {
    Crew(
        it.role,
        it.name,
        it.profilePath?.let { profilePath ->
            String.format(
                BASE_WIDTH_342_PATH,
                profilePath
            )
        },
        it.gender.toGender(),
        it.id
    )
}

private fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE

private const val PROFILE_PATH = "profile_path"