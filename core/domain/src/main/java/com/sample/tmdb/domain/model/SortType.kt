package com.sample.tmdb.domain.model

import com.sample.tmdb.navigation.model.SortType as NavSortType

enum class SortType {
    TRENDING,
    MOST_POPULAR,
    NOW_PLAYING,
    UPCOMING,
    HIGHEST_RATED,
    DISCOVER,
    SIMILAR,
}

fun NavSortType.asDomainModel(): SortType = when (this) {
    NavSortType.TRENDING -> SortType.TRENDING
    NavSortType.MOST_POPULAR -> SortType.MOST_POPULAR
    NavSortType.NOW_PLAYING -> SortType.NOW_PLAYING
    NavSortType.UPCOMING -> SortType.UPCOMING
    NavSortType.HIGHEST_RATED -> SortType.HIGHEST_RATED
    NavSortType.DISCOVER -> SortType.DISCOVER
    NavSortType.SIMILAR -> SortType.SIMILAR
}

fun SortType.asNavSortType(): NavSortType = when (this) {
    SortType.TRENDING -> NavSortType.TRENDING
    SortType.MOST_POPULAR -> NavSortType.MOST_POPULAR
    SortType.NOW_PLAYING -> NavSortType.NOW_PLAYING
    SortType.UPCOMING -> NavSortType.UPCOMING
    SortType.HIGHEST_RATED -> NavSortType.HIGHEST_RATED
    SortType.DISCOVER -> NavSortType.DISCOVER
    SortType.SIMILAR -> NavSortType.SIMILAR
}