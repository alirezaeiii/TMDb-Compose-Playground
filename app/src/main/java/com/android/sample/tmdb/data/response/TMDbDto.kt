package com.android.sample.tmdb.data.response

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.utils.Constants.BASE_WIDTH_342_PATH
import com.android.sample.tmdb.utils.Constants.BASE_WIDTH_780_PATH
import com.squareup.moshi.Json

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

data class NetworkMovie(
    override val id: Int,
    override val overview: String,
    @Json(name = "release_date")
    override val releaseDate: String?,
    @Json(name = POSTER_PATH)
    override val posterPath: String?,
    @Json(name = BACKDROP_PATH)
    override val backdropPath: String?,
    @Json(name = "title")
    override val name: String,
    @Json(name = VOTE_AVERAGE)
    override val voteAverage: Double,
    @Json(name = VOTE_COUNT)
    override val voteCount: Int
) : NetworkTMDbItem

data class NetworkTVShow(
    override val id: Int,
    override val overview: String,
    @Json(name = "first_air_date")
    override val releaseDate: String?,
    @Json(name = POSTER_PATH)
    override val posterPath: String?,
    @Json(name = BACKDROP_PATH)
    override val backdropPath: String?,
    override val name: String,
    @Json(name = VOTE_AVERAGE)
    override val voteAverage: Double,
    @Json(name = VOTE_COUNT)
    override val voteCount: Int
) : NetworkTMDbItem

fun List<NetworkMovie>.asMovieDomainModel(): List<Movie> =
    map {
        Movie(
            it.id,
            it.overview,
            it.releaseDate,
            it.posterPath?.let { posterPath ->
                String.format(
                    BASE_WIDTH_342_PATH,
                    posterPath
                )
            },
            it.backdropPath?.let { backdropPath ->
                String.format(
                    BASE_WIDTH_780_PATH,
                    backdropPath
                )
            },
            it.name,
            it.voteAverage,
            it.voteCount
        )
    }

fun List<NetworkTVShow>.asTVShowDomainModel(): List<TVShow> =
    map {
        TVShow(
            it.id,
            it.overview,
            it.releaseDate,
            it.posterPath?.let { posterPath ->
                String.format(
                    BASE_WIDTH_342_PATH,
                    posterPath
                )
            },
            it.backdropPath?.let { backdropPath ->
                String.format(
                    BASE_WIDTH_780_PATH,
                    backdropPath
                )
            },
            it.name,
            it.voteAverage,
            it.voteCount
        )
    }

private const val POSTER_PATH = "poster_path"
private const val BACKDROP_PATH = "backdrop_path"
private const val VOTE_AVERAGE = "vote_average"
private const val VOTE_COUNT = "vote_count"