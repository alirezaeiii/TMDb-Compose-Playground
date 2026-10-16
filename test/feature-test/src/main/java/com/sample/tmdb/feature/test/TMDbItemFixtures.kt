package com.sample.tmdb.feature.test

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.navigation.TMDbNavKey

interface TMDbItemFixtures<T : TMDbItem> {
    val item: T
    val navKey: TMDbNavKey
}
