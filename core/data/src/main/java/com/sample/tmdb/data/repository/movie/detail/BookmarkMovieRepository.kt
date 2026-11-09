package com.sample.tmdb.data.repository.movie.detail

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.response.asMovieDatabaseModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

@Singleton
class BookmarkMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieApi: MovieService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseBookmarkRepository<Movie>(context, ioDispatcher) {

    override suspend fun getSuccessResult(isRefreshing: Boolean, id: Nothing?): List<Movie> {
        if (isRefreshing) {
            coroutineScope {
                val jobs = movieDao.getBookmarkIds().map { movieId ->
                    async {
                        movieDao.addBookmark(
                            movieApi.fetchMovieDetail(movieId).asMovieDatabaseModel(),
                        )
                    }
                }
                jobs.awaitAll()
            }
        }
        return movieDao.getBookmarks().asDomainModel()
    }
}
