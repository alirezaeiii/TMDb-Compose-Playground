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
    APPLE_TV,
    NETFLIX,
    AMAZON_PRIME,
}

fun SortType.asDomainModel(): DomainSortType = when (this) {
    SortType.TRENDING -> DomainSortType.TRENDING
    SortType.MOST_POPULAR -> DomainSortType.MOST_POPULAR
    SortType.NOW_PLAYING -> DomainSortType.NOW_PLAYING
    SortType.UPCOMING -> DomainSortType.UPCOMING
    SortType.HIGHEST_RATED -> DomainSortType.HIGHEST_RATED
    SortType.DISCOVER -> DomainSortType.DISCOVER
    SortType.SIMILAR -> DomainSortType.SIMILAR
    SortType.APPLE_TV -> DomainSortType.APPLE_TV
    SortType.NETFLIX -> DomainSortType.NETFLIX
    SortType.AMAZON_PRIME -> DomainSortType.AMAZON_PRIME
}

fun DomainSortType.asNavSortType(): SortType = when (this) {
    DomainSortType.TRENDING -> SortType.TRENDING
    DomainSortType.MOST_POPULAR -> SortType.MOST_POPULAR
    DomainSortType.NOW_PLAYING -> SortType.NOW_PLAYING
    DomainSortType.UPCOMING -> SortType.UPCOMING
    DomainSortType.HIGHEST_RATED -> SortType.HIGHEST_RATED
    DomainSortType.DISCOVER -> SortType.DISCOVER
    DomainSortType.SIMILAR -> SortType.SIMILAR
    DomainSortType.APPLE_TV -> SortType.APPLE_TV
    DomainSortType.NETFLIX -> SortType.NETFLIX
    DomainSortType.AMAZON_PRIME -> SortType.AMAZON_PRIME
}
