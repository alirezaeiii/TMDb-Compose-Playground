package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseFeedRepository<T : TMDbItem>(context: Context, ioDispatcher: CoroutineDispatcher) :
    BaseCacheRepository<List<FeedWrapper>, Unit, Unit>(context, ioDispatcher) {

    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract suspend fun discoverItems(): List<T>

    protected abstract suspend fun appleTVItems(): List<T>

    protected abstract suspend fun netflixItems(): List<T>

    protected abstract suspend fun amazonPrimeItems(): List<T>

    protected abstract suspend fun googlePlayItems(): List<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override suspend fun fetch(fetchParam: Unit?): List<FeedWrapper> = coroutineScope {
        val trendingDeferred = async { trendingItems() }
        val nowPlayingDeferred = async { nowPlayingItems() }
        val popularDeferred = async { popularItems() }
        val latestDeferred = async { latestItems() }
        val topRatedDeferred = async { topRatedItems() }
        val discoverDeferred = async { discoverItems() }
        val appleTVDeferred = async { appleTVItems() }
        val netflixDeferred = async { netflixItems() }
        val amazonPrimeDeferred = async { amazonPrimeItems() }
        val googlePlayDeferred = async { googlePlayItems() }

        listOf(
            FeedWrapper(trendingDeferred.await(), R.string.text_trending, SortType.TRENDING),
            FeedWrapper(popularDeferred.await(), R.string.text_popular, SortType.MOST_POPULAR),
            FeedWrapper(nowPlayingDeferred.await(), getNowPlayingResId(), SortType.NOW_PLAYING),
            FeedWrapper(discoverDeferred.await(), R.string.text_discover, SortType.DISCOVER),
            FeedWrapper(latestDeferred.await(), getLatestResId(), SortType.UPCOMING),
            FeedWrapper(topRatedDeferred.await(), R.string.text_highest_rate, SortType.HIGHEST_RATED),
            FeedWrapper(appleTVDeferred.await(), R.string.text_apple_tv, SortType.APPLE_TV),
            FeedWrapper(netflixDeferred.await(), R.string.text_netflix, SortType.NETFLIX),
            FeedWrapper(amazonPrimeDeferred.await(), R.string.text_amazon_prime, SortType.AMAZON_PRIME),
            FeedWrapper(googlePlayDeferred.await(), R.string.text_google_play, SortType.GOOGLE_PLAY),
        )
    }

    protected fun createFeedWrappers(itemsBySortType: Map<SortType, List<T>>): List<FeedWrapper> {
        val sortTypes = listOf(
            SortType.TRENDING to R.string.text_trending,
            SortType.MOST_POPULAR to R.string.text_popular,
            SortType.NOW_PLAYING to getNowPlayingResId(),
            SortType.DISCOVER to R.string.text_discover,
            SortType.UPCOMING to getLatestResId(),
            SortType.HIGHEST_RATED to R.string.text_highest_rate,
            SortType.APPLE_TV to R.string.text_apple_tv,
            SortType.NETFLIX to R.string.text_netflix,
            SortType.AMAZON_PRIME to R.string.text_amazon_prime,
            SortType.GOOGLE_PLAY to R.string.text_google_play,
        )
        return sortTypes.mapNotNull { (sortType, resId) ->
            itemsBySortType[sortType]?.let { feeds ->
                FeedWrapper(feeds, resId, sortType)
            }
        }
    }
}
