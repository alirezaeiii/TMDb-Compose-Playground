package com.sample.tmdb.data.response

import com.sample.tmdb.data.utils.Constants.BACKDROP_PATH
import com.sample.tmdb.data.utils.Constants.BASE_WIDTH_342_PATH
import com.sample.tmdb.data.utils.Constants.BASE_WIDTH_780_PATH
import com.sample.tmdb.data.utils.Constants.FIRST_AIR_DATE
import com.sample.tmdb.data.utils.Constants.ID
import com.sample.tmdb.data.utils.Constants.NAME
import com.sample.tmdb.data.utils.Constants.OVERVIEW
import com.sample.tmdb.data.utils.Constants.POSTER_PATH
import com.sample.tmdb.data.utils.Constants.RELEASE_DATE
import com.sample.tmdb.data.utils.Constants.TITLE
import com.sample.tmdb.data.utils.Constants.VOTE_AVERAGE
import com.sample.tmdb.data.utils.Constants.VOTE_COUNT
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface TMDbItemResponse {
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
data class MovieResponse(
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
) : TMDbItemResponse

@JsonClass(generateAdapter = true)
data class TVShowResponse(
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
) : TMDbItemResponse

fun List<MovieResponse>.asMovieDomainModel() = map(MovieResponse::asMovieDomainModel)

fun List<TVShowResponse>.asTVShowDomainModel() = map(TVShowResponse::asTVShowDomainModel)

private fun MovieResponse.asMovieDomainModel(): Movie = Movie(
    id,
    overview,
    releaseDate,
    posterPath?.let { posterPath ->
        String.format(
            BASE_WIDTH_342_PATH,
            posterPath,
        )
    },
    backdropPath?.let { backdropPath ->
        String.format(
            BASE_WIDTH_780_PATH,
            backdropPath,
        )
    },
    name,
    voteAverage,
    voteCount,
)

private fun TVShowResponse.asTVShowDomainModel(): TVShow = TVShow(
    id,
    overview,
    releaseDate,
    posterPath?.let { posterPath ->
        String.format(
            BASE_WIDTH_342_PATH,
            posterPath,
        )
    },
    backdropPath?.let { backdropPath ->
        String.format(
            BASE_WIDTH_780_PATH,
            backdropPath,
        )
    },
    name,
    voteAverage,
    voteCount,
)
