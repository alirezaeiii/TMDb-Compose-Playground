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
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.model.SpokenLanguage
import com.sample.tmdb.domain.model.TvDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface TMDbItemDetailsResponse {
    val backdropPath: String?
    val genres: List<GenreResponse>
    val homepage: String?
    val id: Int
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String?
    val releaseDate: String?
    val spokenLanguages: List<SpokenLanguageResponse>
    val status: String
    val tagline: String
    val title: String
    val voteAverage: Double
    val voteCount: Int
}

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    @Json(name = BACKDROP_PATH) override val backdropPath: String?,
    @Json(name = GENRES) override val genres: List<GenreResponse>,
    @Json(name = HOMEPAGE) override val homepage: String?,
    @Json(name = ID) override val id: Int,
    @Json(name = ORIGINAL_LANGUAGE) override val originalLanguage: String,
    @Json(name = ORIGINAL_TITLE) override val originalTitle: String,
    @Json(name = OVERVIEW) override val overview: String,
    @Json(name = POPULARITY) override val popularity: Double,
    @Json(name = POSTER_PATH) override val posterPath: String?,
    @Json(name = RELEASE_DATE) override val releaseDate: String?,
    @Json(name = SPOKEN_LANGUAGE) override val spokenLanguages: List<SpokenLanguageResponse>,
    @Json(name = STATUS) override val status: String,
    @Json(name = TAGLINE) override val tagline: String,
    @Json(name = TITLE) override val title: String,
    @Json(name = VOTE_AVERAGE) override val voteAverage: Double,
    @Json(name = VOTE_COUNT) override val voteCount: Int,
) : TMDbItemDetailsResponse

@JsonClass(generateAdapter = true)
data class TvDetailResponse(
    @Json(name = BACKDROP_PATH) override val backdropPath: String?,
    @Json(name = GENRES) override val genres: List<GenreResponse>,
    @Json(name = HOMEPAGE) override val homepage: String?,
    @Json(name = ID) override val id: Int,
    @Json(name = ORIGINAL_LANGUAGE) override val originalLanguage: String,
    @Json(name = ORIGINAL_NAME) override val originalTitle: String,
    @Json(name = OVERVIEW) override val overview: String,
    @Json(name = POPULARITY) override val popularity: Double,
    @Json(name = POSTER_PATH) override val posterPath: String?,
    @Json(name = FIRST_AIR_DATE) override val releaseDate: String?,
    @Json(name = SPOKEN_LANGUAGE) override val spokenLanguages: List<SpokenLanguageResponse>,
    @Json(name = STATUS) override val status: String,
    @Json(name = TAGLINE) override val tagline: String,
    @Json(name = NAME) override val title: String,
    @Json(name = VOTE_AVERAGE) override val voteAverage: Double,
    @Json(name = VOTE_COUNT) override val voteCount: Int,
) : TMDbItemDetailsResponse

@JsonClass(generateAdapter = true)
data class GenreResponse(@Json(name = ID) val id: Int, @Json(name = NAME) val name: String?)

@JsonClass(generateAdapter = true)
data class SpokenLanguageResponse(@Json(name = "iso_639_1") val iso6391: String, @Json(name = NAME) val name: String)

fun MovieDetailResponse.asDomainModel(): MovieDetails = MovieDetails(
    backdropPath?.let {
        String.format(
            BASE_WIDTH_780_PATH,
            it,
        )
    },
    genres.asGenreDomainModel(),
    homepage,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath?.let {
        String.format(
            BASE_WIDTH_342_PATH,
            it,
        )
    },
    releaseDate,
    spokenLanguages.asLanguageDomainModel(),
    status,
    tagline,
    title,
    voteAverage,
    voteCount,
)

fun TvDetailResponse.asDomainModel(): TvDetails = TvDetails(
    backdropPath?.let {
        String.format(
            BASE_WIDTH_780_PATH,
            it,
        )
    },
    genres.asGenreDomainModel(),
    homepage,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath?.let {
        String.format(
            BASE_WIDTH_342_PATH,
            it,
        )
    },
    releaseDate,
    spokenLanguages.asLanguageDomainModel(),
    status,
    tagline,
    title,
    voteAverage,
    voteCount,
)

private fun List<GenreResponse>.asGenreDomainModel(): List<Genre> = map {
    Genre(it.id, it.name)
}

private fun List<SpokenLanguageResponse>.asLanguageDomainModel(): List<SpokenLanguage> = map {
    SpokenLanguage(it.iso6391, it.name)
}

private const val GENRES = "genres"
private const val HOMEPAGE = "homepage"
private const val ORIGINAL_LANGUAGE = "original_language"
private const val ORIGINAL_TITLE = "original_title"
private const val ORIGINAL_NAME = "original_name"
private const val POPULARITY = "popularity"
private const val SPOKEN_LANGUAGE = "spoken_languages"
private const val STATUS = "status"
private const val TAGLINE = "tagline"
