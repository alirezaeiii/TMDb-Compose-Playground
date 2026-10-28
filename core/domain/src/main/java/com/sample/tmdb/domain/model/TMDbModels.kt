package com.sample.tmdb.domain.model

interface TMDbItem {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterUrl: String?
    val backdropUrl: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int
}

data class Movie(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int,
) : TMDbItem

data class TVShow(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int,
) : TMDbItem
