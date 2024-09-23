package com.sample.tmdb.domain.model

import com.sample.tmdb.common.model.TMDbItem

class DetailWrapper(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val details: TMDbItemDetails,
    val images: List<TMDbImage>,
    val similarItems: List<TMDbItem>,
)
