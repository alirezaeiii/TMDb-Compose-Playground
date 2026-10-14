package com.sample.tmdb.feature.test

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.navigation.TMDbNavKey

interface MovieFixtures : TMDbItemFixtures {
    override val item: TMDbItem
        get() = Movie(10, "", null, null, null, "", 0.0, 0)
    override val navKey: TMDbNavKey
        get() = TMDbNavKey.MovieDetail(10)
}
