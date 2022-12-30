package com.android.sample.tmdb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface TMDbItem : Parcelable {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
}

@Parcelize
data class Movie(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val name: String,
    override val voteAverage: Double
) : TMDbItem

@Parcelize
data class TVShow(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val name: String,
    override val voteAverage: Double
) : TMDbItem