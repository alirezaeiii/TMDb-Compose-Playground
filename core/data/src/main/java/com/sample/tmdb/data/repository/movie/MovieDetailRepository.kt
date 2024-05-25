package com.sample.tmdb.data.repository.movie

import android.content.Context
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.response.asCastDomainModel
import com.sample.tmdb.data.response.asCrewDomainModel
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.repository.BaseDetailRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRepository @Inject constructor(
    private val movieApi: MovieService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<MovieDetails>(context, ioDispatcher) {

    override suspend fun getDetails(id: Int): MovieDetails =
        movieApi.fetchMovieDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = movieApi.movieCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel()
        )
    }

    override suspend fun getImages(id: Int): List<TMDbImage> =
        movieApi.fetchImages(id).asDomainModel()

    override suspend fun getSimilarItems(id: Int): List<TMDbItem> =
        movieApi.fetchSimilarMovies(id).items.asMovieDomainModel()
}