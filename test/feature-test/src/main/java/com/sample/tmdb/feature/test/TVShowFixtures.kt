package com.sample.tmdb.feature.test

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.TMDbNavKey

interface TVShowFixtures : TMDbItemFixtures<TVShow> {
    override val item: TVShow get() = TVShow(10, "", null, null, null, "", 0.0, 0)
    override val navKey: TMDbNavKey get() = TMDbNavKey.TvShowDetail(10)
}
