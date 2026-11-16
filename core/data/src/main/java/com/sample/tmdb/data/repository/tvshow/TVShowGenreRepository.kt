package com.sample.tmdb.data.repository.tvshow

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.entity.asTVShowGenreDatabaseModel
import com.sample.tmdb.data.source.local.TVShowFeedDao
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.repository.BaseCacheRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class TVShowGenreRepository @Inject constructor(
    private val tvShowApi: TVShowService,
    private val tvShowFeedDao: TVShowFeedDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseCacheRepository<List<Genre>, Unit, Unit>(
    context = context,
    ioDispatcher = ioDispatcher,
    cacheDurationMs = 24 * 60 * 60 * 1000L,
) {
    override fun getCacheKey(): Any = CACHE_KEY

    override suspend fun query(queryParam: Unit?): List<Genre>? {
        val dbGenres = tvShowFeedDao.getGenres()
        return if (dbGenres.isEmpty()) null else dbGenres.asDomainModel()
    }

    override suspend fun fetch(fetchParam: Unit?): List<Genre> = tvShowApi.fetchGenres().asDomainModel()

    override suspend fun saveFetchResult(item: List<Genre>) {
        tvShowFeedDao.deleteAndInsertGenres(item.asTVShowGenreDatabaseModel())
    }

    companion object {
        private const val CACHE_KEY = "Genres_tv"
    }
}
