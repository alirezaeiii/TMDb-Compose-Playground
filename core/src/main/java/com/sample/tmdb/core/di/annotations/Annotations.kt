package com.sample.tmdb.core.di.annotations

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Popular

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NowPlaying

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Latest

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TopRated

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Trending

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Discover

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Search