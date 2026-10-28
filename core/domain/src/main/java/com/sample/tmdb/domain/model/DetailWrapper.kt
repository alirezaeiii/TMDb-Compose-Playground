package com.sample.tmdb.domain.model

class DetailWrapper(
    val cast: List<TrendingPerson>,
    val crew: List<TrendingPerson>,
    val details: TMDbItemDetails,
    val images: List<TMDbImage>,
    val similarItems: List<TMDbItem>,
)
