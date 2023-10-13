package com.sample.tmdb.core.domain.model

class DetailWrapper<T : TMDbItemDetails>(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val details: T
)