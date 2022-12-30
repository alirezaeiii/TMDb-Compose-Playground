package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.data.network.MovieService
import com.android.sample.tmdb.di.IoDispatcher
import com.android.sample.tmdb.domain.BaseDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRepository @Inject constructor(
    private val movieId: Int,
    private val movieApi: MovieService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository(context, ioDispatcher) {

    override suspend fun getCredit() = movieApi.movieCredit(movieId)
}