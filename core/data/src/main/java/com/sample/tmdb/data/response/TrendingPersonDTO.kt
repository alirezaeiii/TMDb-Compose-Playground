package com.sample.tmdb.data.response

import com.sample.tmdb.common.utils.Constants.BASE_WIDTH_342_PATH
import com.sample.tmdb.common.utils.Constants.GENDER
import com.sample.tmdb.common.utils.Constants.ID
import com.sample.tmdb.common.utils.Constants.NAME
import com.sample.tmdb.common.utils.Constants.PROFILE_PATH
import com.sample.tmdb.common.utils.toGender
import com.sample.tmdb.navigation.TrendingPerson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TrendingPersonDTO(
    @Json(name = ID)
    val id: Int,
    @Json(name = NAME)
    val name: String,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = GENDER)
    val gender: Int,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    @Json(name = "known_for")
    val knownFor: List<MediaDTO>? = null,
)

fun List<TrendingPersonDTO>.asDomainModel() = map(TrendingPersonDTO::asDomainModel)

fun TrendingPersonDTO.asDomainModel(): TrendingPerson = TrendingPerson(
    id = id,
    name = name,
    subtitle = originalName,
    gender = gender.toGender(),
    profileUrl = profilePath?.let { profilePath ->
        String.format(
            BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
    knownFor = knownFor?.asDomainModel(),
)
