package com.sample.tmdb.ui

import com.sample.tmdb.common.ui.TMDbNavKey
import kotlinx.serialization.Serializable

@Serializable
data object Movie : TMDbNavKey

@Serializable
data object TvShow : TMDbNavKey

@Serializable
data object Bookmark : TMDbNavKey

@Serializable
data object Setting : TMDbNavKey
