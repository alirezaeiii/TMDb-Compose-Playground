package com.sample.tmdb.domain.model

interface TMDbItemDetails {
    val backdropPath: String?
    val genres: List<Genre>
    val homepage: String?
    val id: Int
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String?
    val releaseDate: String?
    val spokenLanguages: List<SpokenLanguage>
    val status: String
    val tagline: String
    val title: String
    val voteAverage: Double
    val voteCount: Int
}

data class MovieDetails(
    override val backdropPath: String?,
    override val genres: List<Genre>,
    override val homepage: String?,
    override val id: Int,
    override val originalLanguage: String,
    override val originalTitle: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String?,
    override val releaseDate: String?,
    override val spokenLanguages: List<SpokenLanguage>,
    override val status: String,
    override val tagline: String,
    override val title: String,
    override val voteAverage: Double,
    override val voteCount: Int
) : TMDbItemDetails

data class TvDetails(
    override val backdropPath: String?,
    override val genres: List<Genre>,
    override val homepage: String?,
    override val id: Int,
    override val originalLanguage: String,
    override val originalTitle: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String?,
    override val releaseDate: String?,
    override val spokenLanguages: List<SpokenLanguage>,
    override val status: String,
    override val tagline: String,
    override val title: String,
    override val voteAverage: Double,
    override val voteCount: Int,
) : TMDbItemDetails

data class Genre(
    val id: Int,
    val name: String?
)

data class SpokenLanguage(
    val iso6391: String,
    val name: String
)