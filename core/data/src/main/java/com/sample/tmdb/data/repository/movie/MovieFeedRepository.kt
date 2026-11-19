package com.sample.tmdb.data.repository.movie

import android.content.Context
import com.sample.tmdb.data.R
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.entity.asFeedDatabaseModel
import com.sample.tmdb.data.source.local.MovieFeedDao
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.utils.Async
import com.sample.tmdb.domain.utils.CacheTimestampDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.last

@Singleton
class MovieFeedRepository @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val movieApi: MovieService,
    private val movieFeedDao: MovieFeedDao,
    private val movieGenreRepository: MovieGenreRepository,
    cacheTimestampStore: CacheTimestampDataStore,
) : BaseFeedRepository<Movie>(context, ioDispatcher, cacheTimestampStore) {
    override suspend fun popularItems(): List<Movie> = movieApi.popularMovies().items.asMovieDomainModel()

    override suspend fun latestItems(): List<Movie> = movieApi.upcomingMovies().items.asMovieDomainModel()

    override suspend fun topRatedItems(): List<Movie> = movieApi.topRatedMovies().items.asMovieDomainModel()

    override suspend fun trendingItems(): List<Movie> = movieApi.trendingMovies().items.asMovieDomainModel()

    override suspend fun nowPlayingItems(): List<Movie> = movieApi.nowPlayingMovies().items.asMovieDomainModel()

    override suspend fun discoverItems(genres: Array<Int>): List<Movie> =
        movieApi.discoverMovies(genres = genres.toQueryString()).items.asMovieDomainModel()

    override suspend fun appleTVItems(genres: Array<Int>): List<Movie> =
        movieApi.providerMovies(APPLE_TV_PROVIDER_ID, genres = genres.toQueryString()).items.asMovieDomainModel()

    override suspend fun netflixItems(genres: Array<Int>): List<Movie> =
        movieApi.providerMovies(NETFLIX_PROVIDER_ID, genres = genres.toQueryString()).items.asMovieDomainModel()

    override suspend fun amazonPrimeItems(genres: Array<Int>): List<Movie> =
        movieApi.providerMovies(AMAZON_PRIME_PROVIDER_ID, genres = genres.toQueryString()).items.asMovieDomainModel()

    override suspend fun googlePlayItems(genres: Array<Int>): List<Movie> =
        movieApi.providerMovies(GOOGLE_PLAY_PROVIDER_ID, genres = genres.toQueryString()).items.asMovieDomainModel()

    override suspend fun getGenres(): List<Genre> {
        val result = movieGenreRepository.getResult(forceRefresh = false).last()
        return (result as? Async.Success)?.data ?: emptyList()
    }

    override fun getNowPlayingResId(): Int = R.string.text_now_playing

    override fun getLatestResId(): Int = R.string.text_upcoming

    override suspend fun query(queryParam: Unit?): List<FeedWrapper> {
        val genresKey = selectedGenres.sortedArray().joinToString(",")
        val dbEntities = movieFeedDao.getFeed(genresKey)
        if (dbEntities.isEmpty()) {
            return emptyList()
        }
        val genres = movieFeedDao.getGenres().asDomainModel()
        val itemsBySortType = dbEntities
            .groupBy { it.sortType }
            .mapValues { entry -> entry.value.map { it.asDomainModel() } }
        return createFeedWrappers(itemsBySortType, genres)
    }

    override suspend fun saveFetchResult(item: List<FeedWrapper>) {
        val genresKey = selectedGenres.sortedArray().joinToString(",")
        val entities = item.flatMap { feedWrapper ->
            feedWrapper.feeds.filterIsInstance<Movie>().mapIndexed { index, movie ->
                movie.asFeedDatabaseModel(feedWrapper.sortType, index, genresKey)
            }
        }
        movieFeedDao.deleteAndInsert(genresKey, entities)
    }
}
