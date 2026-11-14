package com.sample.tmdb.data.repository.tvshow

import android.content.Context
import com.sample.tmdb.data.R
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.entity.asFeedDatabaseModel
import com.sample.tmdb.data.source.local.TVShowFeedDao
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

private fun Array<Int>.toQueryString(): String? = if (isEmpty()) null else joinToString(",")

@Singleton
class TVShowFeedRepository @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val tvShowApi: TVShowService,
    private val tvShowFeedDao: TVShowFeedDao,
) : BaseFeedRepository<TVShow>(context, ioDispatcher) {
    override suspend fun popularItems(genres: Array<Int>): List<TVShow> = if (genres.isEmpty()) {
        tvShowApi.popularTVSeries().items.asTVShowDomainModel()
    } else {
        tvShowApi.discoverTVSeries(genres = genres.toQueryString()).items.asTVShowDomainModel()
    }

    override suspend fun latestItems(genres: Array<Int>): List<TVShow> = if (genres.isEmpty()) {
        tvShowApi.onTheAirTVSeries().items.asTVShowDomainModel()
    } else {
        tvShowApi.discoverTVSeries(genres = genres.toQueryString()).items.asTVShowDomainModel()
    }

    override suspend fun topRatedItems(genres: Array<Int>): List<TVShow> = if (genres.isEmpty()) {
        tvShowApi.topRatedTVSeries().items.asTVShowDomainModel()
    } else {
        tvShowApi.discoverTVSeries(genres = genres.toQueryString()).items.asTVShowDomainModel()
    }

    override suspend fun trendingItems(genres: Array<Int>): List<TVShow> = if (genres.isEmpty()) {
        tvShowApi.trendingTVSeries().items.asTVShowDomainModel()
    } else {
        tvShowApi.discoverTVSeries(genres = genres.toQueryString()).items.asTVShowDomainModel()
    }

    override suspend fun nowPlayingItems(genres: Array<Int>): List<TVShow> = if (genres.isEmpty()) {
        tvShowApi.airingTodayTVSeries().items.asTVShowDomainModel()
    } else {
        tvShowApi.discoverTVSeries(genres = genres.toQueryString()).items.asTVShowDomainModel()
    }

    override suspend fun discoverItems(genres: Array<Int>): List<TVShow> =
        tvShowApi.discoverTVSeries(genres = genres.toQueryString()).items.asTVShowDomainModel()

    override suspend fun appleTVItems(genres: Array<Int>): List<TVShow> =
        tvShowApi.providerTVSeries(APPLE_TV_PROVIDER_ID, genres = genres.toQueryString()).items.asTVShowDomainModel()

    override suspend fun netflixItems(genres: Array<Int>): List<TVShow> =
        tvShowApi.providerTVSeries(NETFLIX_PROVIDER_ID, genres = genres.toQueryString()).items.asTVShowDomainModel()

    override suspend fun amazonPrimeItems(genres: Array<Int>): List<TVShow> = tvShowApi.providerTVSeries(
        AMAZON_PRIME_PROVIDER_ID,
        genres = genres.toQueryString(),
    ).items.asTVShowDomainModel()

    override suspend fun googlePlayItems(genres: Array<Int>): List<TVShow> =
        tvShowApi.providerTVSeries(GOOGLE_PLAY_PROVIDER_ID, genres = genres.toQueryString()).items.asTVShowDomainModel()

    override suspend fun getGenres(): List<Genre> = tvShowApi.fetchGenres().asDomainModel()

    override fun getNowPlayingResId(): Int = R.string.text_airing_today

    override fun getLatestResId(): Int = R.string.text_on_the_air

    override suspend fun query(queryParam: Unit?): List<FeedWrapper> {
        val genresKey = selectedGenres.sortedArray().joinToString(",")
        val dbEntities = tvShowFeedDao.getFeed(genresKey)
        if (dbEntities.isEmpty()) return emptyList()
        val itemsBySortType = dbEntities
            .groupBy { it.sortType }
            .mapValues { entry -> entry.value.map { it.asDomainModel() } }
        return createFeedWrappers(itemsBySortType)
    }

    override suspend fun saveFetchResult(item: List<FeedWrapper>) {
        val genresKey = selectedGenres.sortedArray().joinToString(",")
        val entities = item.flatMap { feedWrapper ->
            feedWrapper.feeds.filterIsInstance<TVShow>().mapIndexed { index, tvShow ->
                tvShow.asFeedDatabaseModel(feedWrapper.sortType, index, genresKey)
            }
        }
        tvShowFeedDao.deleteAndInsert(genresKey, entities)
    }
}
