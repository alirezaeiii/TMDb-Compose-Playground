package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.data.network.MovieService
import com.android.sample.tmdb.data.response.asDomainModel
import com.android.sample.tmdb.di.IoDispatcher
import com.android.sample.tmdb.domain.BaseDetailRepository
import com.android.sample.tmdb.domain.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRepository @Inject constructor(
    private val movieApi: MovieService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<MovieDetails>(context, ioDispatcher) {

    override suspend fun getDetails(id: Int): MovieDetails =
        movieApi.fetchMovieDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int) = movieApi.movieCredit(id)
}