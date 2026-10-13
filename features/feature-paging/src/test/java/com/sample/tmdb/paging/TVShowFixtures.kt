package com.sample.tmdb.paging

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.TMDbNavKey

interface TVShowFixtures : TMDbItemFixtures {
    override val item: TMDbItem get() = TVShow(10, "", null, null, null, "", 0.0, 0)
    override val navKey: TMDbNavKey get() = TMDbNavKey.TvShowDetail(10)
}
