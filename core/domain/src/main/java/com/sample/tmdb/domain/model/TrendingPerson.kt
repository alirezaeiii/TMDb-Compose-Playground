package com.sample.tmdb.domain.model

import com.sample.tmdb.navigation.model.Gender
import com.sample.tmdb.navigation.model.MediaType
import com.sample.tmdb.navigation.model.TrendingPerson as NavTrendingPerson

data class TrendingPerson(
    val id: Int,
    val name: String,
    val subtitle: String,
    val gender: Gender,
    val profileUrl: String?,
    val knownFor: List<Media>? = null,
)

fun TrendingPerson.asNavPerson(): NavTrendingPerson = NavTrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = gender,
    profileUrl = profileUrl,
    knownFor = knownFor?.asNavMedia(),
)

fun List<TrendingPerson>.asNavPerson(): List<NavTrendingPerson> = map(TrendingPerson::asNavPerson)

fun NavTrendingPerson.asDomainModel(): TrendingPerson = TrendingPerson(
    id = id,
    name = name,
    subtitle = subtitle,
    gender = gender,
    profileUrl = profileUrl,
    knownFor = knownFor?.map { navMedia ->
        if (navMedia.mediaType == MediaType.MOVIE) {
            MovieMedia(navMedia.id, navMedia.posterUrl, navMedia.mediaType, navMedia.title)
        } else {
            TVShowMedia(navMedia.id, navMedia.posterUrl, navMedia.mediaType, navMedia.title)
        }
    },
)

fun List<NavTrendingPerson>.asDomainModel(): List<TrendingPerson> = map(NavTrendingPerson::asDomainModel)
