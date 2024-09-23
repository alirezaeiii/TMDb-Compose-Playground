package com.sample.tmdb.common.model

import android.os.Parcelable

interface TMDbItem : Parcelable {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterUrl: String?
    val backdropUrl: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int
}
