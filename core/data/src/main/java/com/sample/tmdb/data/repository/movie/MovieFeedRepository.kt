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
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class MovieFeedRepository @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val movieApi: MovieService,
    private val movieFeedDao: MovieFeedDao,
) : BaseFeedRepository<Movie>(context, ioDispatcher) {
    override suspend fun popularItems(): List<Movie> = movieApi.popularMovies().items.asMovieDomainModel()

    override suspend fun latestItems(): List<Movie> = movieApi.upcomingMovies().items.asMovieDomainModel()

    override suspend fun topRatedItems(): List<Movie> = movieApi.topRatedMovies().items.asMovieDomainModel()

    override suspend fun trendingItems(): List<Movie> = movieApi.trendingMovies().items.asMovieDomainModel()

    override suspend fun nowPlayingItems(): List<Movie> = movieApi.nowPlayingMovies().items.asMovieDomainModel()

    override suspend fun discoverItems(): List<Movie> = movieApi.discoverMovies().items.asMovieDomainModel()

    override suspend fun appleTVItems(): List<Movie> =
        movieApi.providerMovies(APPLE_TV_PROVIDER_ID).items.asMovieDomainModel()

    override suspend fun netflixItems(): List<Movie> =
        movieApi.providerMovies(NETFLIX_PROVIDER_ID).items.asMovieDomainModel()

    override suspend fun amazonPrimeItems(): List<Movie> =
        movieApi.providerMovies(AMAZON_PRIME_PROVIDER_ID).items.asMovieDomainModel()

    override suspend fun googlePlayItems(): List<Movie> =
        movieApi.providerMovies(GOOGLE_PLAY_PROVIDER_ID).items.asMovieDomainModel()

    override fun getNowPlayingResId(): Int = R.string.text_now_playing

    override fun getLatestResId(): Int = R.string.text_upcoming

    override suspend fun query(queryParam: Unit?): List<FeedWrapper> {
        val dbEntities = movieFeedDao.getFeed()
        if (dbEntities.isEmpty()) return emptyList()
        val itemsBySortType = dbEntities
            .groupBy { it.sortType }
            .mapValues { entry -> entry.value.map { it.asDomainModel() } }
        return createFeedWrappers(itemsBySortType)
    }

    override suspend fun saveFetchResult(item: List<FeedWrapper>) {
        val entities = item.flatMap { feedWrapper ->
            feedWrapper.feeds.filterIsInstance<Movie>().mapIndexed { index, movie ->
                movie.asFeedDatabaseModel(feedWrapper.sortType, index)
            }
        }
        movieFeedDao.deleteAndInsert(entities)
    }
}
