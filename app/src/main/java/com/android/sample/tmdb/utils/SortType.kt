package com.android.sample.tmdb.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class SortType : Parcelable {
    TRENDING,
    MOST_POPULAR,
    NOW_PLAYING,
    UPCOMING,
    HIGHEST_RATED
}