package com.sample.tmdb.navigation.model

import com.sample.tmdb.domain.model.SortType as DomainSortType

enum class SortType {
    TRENDING,
    MOST_POPULAR,
    NOW_PLAYING,
    UPCOMING,
    HIGHEST_RATED,
    DISCOVER,
    SIMILAR,
}

fun SortType.asDomainModel(): DomainSortType = when (this) {
    SortType.TRENDING -> DomainSortType.TRENDING
    SortType.MOST_POPULAR -> DomainSortType.MOST_POPULAR
    SortType.NOW_PLAYING -> DomainSortType.NOW_PLAYING
    SortType.UPCOMING -> DomainSortType.UPCOMING
    SortType.HIGHEST_RATED -> DomainSortType.HIGHEST_RATED
    SortType.DISCOVER -> DomainSortType.DISCOVER
    SortType.SIMILAR -> DomainSortType.SIMILAR
}

fun DomainSortType.asNavSortType(): SortType = when (this) {
    DomainSortType.TRENDING -> SortType.TRENDING
    DomainSortType.MOST_POPULAR -> SortType.MOST_POPULAR
    DomainSortType.NOW_PLAYING -> SortType.NOW_PLAYING
    DomainSortType.UPCOMING -> SortType.UPCOMING
    DomainSortType.HIGHEST_RATED -> SortType.HIGHEST_RATED
    DomainSortType.DISCOVER -> SortType.DISCOVER
    DomainSortType.SIMILAR -> SortType.SIMILAR
}
