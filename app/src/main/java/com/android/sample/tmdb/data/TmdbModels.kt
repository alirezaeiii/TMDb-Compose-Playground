package com.android.sample.tmdb.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

interface TMDbItem : Parcelable {
    val id : Int
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
    @Json(name = "release_date")
    override val releaseDate: String?,
    @Json(name = "poster_path")
    override val posterPath: String?,
    @Json(name = "backdrop_path")
    override val backdropPath: String?,
    @Json(name = "title")
    override val name: String,
    @Json(name = "vote_average")
    override val voteAverage: Double) : TMDbItem

@Parcelize
data class TVShow(
    override val id: Int,
    override val overview: String,
    @Json(name = "first_air_date")
    override val releaseDate: String?,
    @Json(name = "poster_path")
    override val posterPath: String?,
    @Json(name = "backdrop_path")
    override val backdropPath: String?,
    override val name: String,
    @Json(name = "vote_average")
    override val voteAverage: Double) : TMDbItem