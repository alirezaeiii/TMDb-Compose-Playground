package com.sample.tmdb.data.response

import com.sample.tmdb.data.repository.BasePagingRepository.Companion.NETWORK_PAGE_SIZE
import com.sample.tmdb.data.source.entity.PersonPagingEntity
import com.sample.tmdb.data.source.entity.PersonSearchPagingEntity
import com.sample.tmdb.data.utils.Constants.BASE_WIDTH_342_PATH
import com.sample.tmdb.data.utils.Constants.GENDER
import com.sample.tmdb.data.utils.Constants.ID
import com.sample.tmdb.data.utils.Constants.NAME
import com.sample.tmdb.data.utils.Constants.PROFILE_PATH
import com.sample.tmdb.data.utils.toGender
import com.sample.tmdb.domain.model.TrendingPerson
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

fun List<TrendingPersonDTO>.asPagingDatabaseModel(page: Int) = mapIndexed { index, person ->
    person.asPagingDatabaseModel((page - 1) * NETWORK_PAGE_SIZE + index)
}

fun List<TrendingPersonDTO>.asSearchPagingDatabaseModel(query: String, page: Int) = mapIndexed { index, person ->
    person.asSearchPagingDatabaseModel(query, (page - 1) * NETWORK_PAGE_SIZE + index)
}

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

fun TrendingPersonDTO.asPagingDatabaseModel(order: Int): PersonPagingEntity = PersonPagingEntity(
    id = id,
    name = name,
    subtitle = originalName,
    gender = gender,
    profileUrl = profilePath?.let { profilePath ->
        String.format(
            BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
    itemOrder = order,
)

fun TrendingPersonDTO.asSearchPagingDatabaseModel(query: String, order: Int): PersonSearchPagingEntity =
    PersonSearchPagingEntity(
        queryStr = query,
        id = id,
        name = name,
        subtitle = originalName,
        gender = gender,
        profileUrl = profilePath?.let { profilePath ->
            String.format(
                BASE_WIDTH_342_PATH,
                profilePath,
            )
        },
        knownFor = knownFor?.asDomainModel(),
        itemOrder = order,
    )
