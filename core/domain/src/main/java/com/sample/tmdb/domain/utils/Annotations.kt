package com.sample.tmdb.domain.utils

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TMDb

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Search

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Similar
