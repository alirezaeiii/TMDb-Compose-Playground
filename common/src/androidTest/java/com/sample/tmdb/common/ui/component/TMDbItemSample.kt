package com.sample.tmdb.common.ui.component

import android.os.Parcel
import com.sample.tmdb.common.model.TMDbItem

internal class TMDbItemSample(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int
) : TMDbItem {
    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel, p1: Int) {
    }
}