package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.navigation.TrendingPerson

class DetailWrapper(
    val cast: List<TrendingPerson>,
    val crew: List<TrendingPerson>,
    val details: TMDbItemDetails,
    val images: List<TMDbImage>,
    val similarItems: List<TMDbItem>,
)
