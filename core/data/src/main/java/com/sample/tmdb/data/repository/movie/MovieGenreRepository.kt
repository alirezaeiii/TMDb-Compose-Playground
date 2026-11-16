package com.sample.tmdb.data.repository.movie

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.entity.asMovieGenreDatabaseModel
import com.sample.tmdb.data.source.local.MovieFeedDao
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.domain.utils.CacheTimestampDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class MovieGenreRepository @Inject constructor(
    private val movieApi: MovieService,
    private val movieFeedDao: MovieFeedDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    cacheTimestampStore: CacheTimestampDataStore,
) : BaseCacheRepository<List<Genre>, Unit, Unit>(
    context = context,
    ioDispatcher = ioDispatcher,
    cacheTimestampStore = cacheTimestampStore,
    cacheDurationMs = 24 * 60 * 60 * 1000L,
) {
    override fun getCacheKey(): String = CACHE_KEY

    override suspend fun query(queryParam: Unit?): List<Genre>? {
        val dbGenres = movieFeedDao.getGenres()
        return if (dbGenres.isEmpty()) null else dbGenres.asDomainModel()
    }

    override suspend fun fetch(fetchParam: Unit?): List<Genre> = movieApi.fetchGenres().asDomainModel()

    override suspend fun saveFetchResult(item: List<Genre>) {
        movieFeedDao.deleteAndInsertGenres(item.asMovieGenreDatabaseModel())
    }

    companion object {
        private const val CACHE_KEY = "Genres_movie"
    }
}
