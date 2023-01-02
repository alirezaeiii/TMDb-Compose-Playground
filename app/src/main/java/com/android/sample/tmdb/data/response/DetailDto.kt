package com.android.sample.tmdb.data.response

import com.android.sample.tmdb.domain.model.Genre
import com.android.sample.tmdb.domain.model.MovieDetails
import com.android.sample.tmdb.domain.model.SpokenLanguage
import com.android.sample.tmdb.domain.model.TvDetails
import com.squareup.moshi.Json

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

data class MovieDetailResponse(
    @Json(name = "backdrop_path") override val backdropPath: String?,
    @Json(name = "genres") override val genres: List<GenreResponse>,
    @Json(name = "homepage") override val homepage: String?,
    @Json(name = "id") override val id: Int,
    @Json(name = "original_language") override val originalLanguage: String,
    @Json(name = "original_title") override val originalTitle: String,
    @Json(name = "overview") override val overview: String,
    @Json(name = "popularity") override val popularity: Double,
    @Json(name = "poster_path") override val posterPath: String?,
    @Json(name = "release_date") override val releaseDate: String?,
    @Json(name = "spoken_languages") override val spokenLanguages: List<SpokenLanguageResponse>,
    @Json(name = "status") override val status: String,
    @Json(name = "tagline") override val tagline: String,
    @Json(name = "title") override val title: String,
    @Json(name = "vote_average") override val voteAverage: Double,
    @Json(name = "vote_count") override val voteCount: Int
) : TMDbItemDetailsResponse

data class TvDetailResponse(
    @Json(name = "backdrop_path") override val backdropPath: String?,
    @Json(name = "genres") override val genres: List<GenreResponse>,
    @Json(name = "homepage") override val homepage: String?,
    @Json(name = "id") override val id: Int,
    @Json(name = "original_language") override val originalLanguage: String,
    @Json(name = "original_name") override val originalTitle: String,
    @Json(name = "overview") override val overview: String,
    @Json(name = "popularity") override val popularity: Double,
    @Json(name = "poster_path") override val posterPath: String?,
    @Json(name = "first_air_date") override val releaseDate: String?,
    @Json(name = "spoken_languages") override val spokenLanguages: List<SpokenLanguageResponse>,
    @Json(name = "status") override val status: String,
    @Json(name = "tagline") override val tagline: String,
    @Json(name = "name") override val title: String,
    @Json(name = "vote_average") override val voteAverage: Double,
    @Json(name = "vote_count") override val voteCount: Int,

) : TMDbItemDetailsResponse

data class GenreResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String?
)

data class SpokenLanguageResponse(
    @Json(name = "iso_639_1") val iso6391: String,
    @Json(name = "name") val name: String
)

fun MovieDetailResponse.asDomainModel(): MovieDetails = MovieDetails(
    backdropPath, genres.asGenreDomainModel(),
    homepage, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate,
    spokenLanguages.asLanguageDomainModel(), status, tagline, title, voteAverage, voteCount
)

fun TvDetailResponse.asDomainModel(): TvDetails = TvDetails(
    backdropPath, genres.asGenreDomainModel(),
    homepage, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate,
    spokenLanguages.asLanguageDomainModel(), status, tagline, title, voteAverage, voteCount
)

private fun List<GenreResponse>.asGenreDomainModel(): List<Genre> = map {
    Genre(it.id, it.name)
}

private fun List<SpokenLanguageResponse>.asLanguageDomainModel(): List<SpokenLanguage> = map {
    SpokenLanguage(it.iso6391, it.name)
}