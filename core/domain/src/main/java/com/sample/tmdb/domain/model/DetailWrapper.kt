package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.TMDbItem

class DetailWrapper<T : TMDbItemDetails>(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val details: T,
    val images: List<TMDbImage>,
    val similarItems: List<TMDbItem>
)