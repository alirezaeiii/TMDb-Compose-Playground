package com.sample.tmdb.paging

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.navigation.TMDbNavKey

interface TMDbItemFixtures {
    val item: TMDbItem
    val navKey: TMDbNavKey
}
