package com.sample.tmdb.data.response

import com.sample.tmdb.common.utils.Constants.BACKDROP_PATH
import com.sample.tmdb.common.utils.Constants.BASE_WIDTH_342_PATH
import com.sample.tmdb.common.utils.Constants.BASE_WIDTH_780_PATH
import com.sample.tmdb.common.utils.Constants.FIRST_AIR_DATE
import com.sample.tmdb.common.utils.Constants.ID
import com.sample.tmdb.common.utils.Constants.NAME
import com.sample.tmdb.common.utils.Constants.OVERVIEW
import com.sample.tmdb.common.utils.Constants.POSTER_PATH
import com.sample.tmdb.common.utils.Constants.RELEASE_DATE
import com.sample.tmdb.common.utils.Constants.TITLE
import com.sample.tmdb.common.utils.Constants.VOTE_AVERAGE
import com.sample.tmdb.common.utils.Constants.VOTE_COUNT
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface NetworkTMDbItem {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int
}

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    @Json(name = ID)
    override val id: Int,
    @Json(name = OVERVIEW)
    override val overview: String,
    @Json(name = RELEASE_DATE)
    override val releaseDate: String?,
    @Json(name = POSTER_PATH)
    override val posterPath: String?,
    @Json(name = BACKDROP_PATH)
    override val backdropPath: String?,
    @Json(name = TITLE)
    override val name: String,
    @Json(name = VOTE_AVERAGE)
    override val voteAverage: Double,
    @Json(name = VOTE_COUNT)
    override val voteCount: Int,
) : NetworkTMDbItem

@JsonClass(generateAdapter = true)
data class NetworkTVShow(
    @Json(name = ID)
    override val id: Int,
    @Json(name = OVERVIEW)
    override val overview: String,
    @Json(name = FIRST_AIR_DATE)
    override val releaseDate: String?,
    @Json(name = POSTER_PATH)
    override val posterPath: String?,
    @Json(name = BACKDROP_PATH)
    override val backdropPath: String?,
    @Json(name = NAME)
    override val name: String,
    @Json(name = VOTE_AVERAGE)
    override val voteAverage: Double,
    @Json(name = VOTE_COUNT)
    override val voteCount: Int,
) : NetworkTMDbItem

fun List<NetworkMovie>.asMovieDomainModel(): List<Movie> = map {
    Movie(
        it.id,
        it.overview,
        it.releaseDate,
        it.posterPath?.let { posterPath ->
            String.format(
                BASE_WIDTH_342_PATH,
                posterPath,
            )
        },
        it.backdropPath?.let { backdropPath ->
            String.format(
                BASE_WIDTH_780_PATH,
                backdropPath,
            )
        },
        it.name,
        it.voteAverage,
        it.voteCount,
    )
}

fun List<NetworkTVShow>.asTVShowDomainModel(): List<TVShow> = map {
    TVShow(
        it.id,
        it.overview,
        it.releaseDate,
        it.posterPath?.let { posterPath ->
            String.format(
                BASE_WIDTH_342_PATH,
                posterPath,
            )
        },
        it.backdropPath?.let { backdropPath ->
            String.format(
                BASE_WIDTH_780_PATH,
                backdropPath,
            )
        },
        it.name,
        it.voteAverage,
        it.voteCount,
    )
}
