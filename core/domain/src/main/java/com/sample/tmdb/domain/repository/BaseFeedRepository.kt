package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.model.Feed
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.utils.CacheTimestampDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseFeedRepository<T : TMDbItem>(
    context: Context,
    ioDispatcher: CoroutineDispatcher,
    cacheTimestampStore: CacheTimestampDataStore,
) : BaseCacheRepository<Feed, Unit, Unit>(
    context = context,
    ioDispatcher = ioDispatcher,
    cacheTimestampStore = cacheTimestampStore,
    cacheDurationMs = 60 * 60 * 1000L,
) {

    private var _selectedGenres: Array<Int> = emptyArray()
    val selectedGenres: Array<Int> get() = _selectedGenres

    fun setSelectedGenres(genres: Array<Int>) {
        _selectedGenres = genres
    }

    override fun getCacheKey(): String? = selectedGenres.sortedArray().joinToString(",")

    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract suspend fun discoverItems(genres: Array<Int>): List<T>

    protected abstract suspend fun appleTVItems(genres: Array<Int>): List<T>

    protected abstract suspend fun netflixItems(genres: Array<Int>): List<T>

    protected abstract suspend fun amazonPrimeItems(genres: Array<Int>): List<T>

    protected abstract suspend fun googlePlayItems(genres: Array<Int>): List<T>

    abstract suspend fun getGenres(): List<Genre>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override suspend fun fetch(fetchParam: Unit?): Feed = coroutineScope {
        val genres = selectedGenres
        val isFiltered = genres.isNotEmpty()
        val genresDeferred = async { getGenres() }
        val trendingDeferred = async {
            fetchUnfilteredItems(isFiltered) { trendingItems() }
        }
        val nowPlayingDeferred = async {
            fetchUnfilteredItems(isFiltered) { nowPlayingItems() }
        }
        val popularDeferred = async {
            fetchUnfilteredItems(isFiltered) { popularItems() }
        }
        val latestDeferred = async {
            fetchUnfilteredItems(isFiltered) { latestItems() }
        }
        val topRatedDeferred = async {
            fetchUnfilteredItems(isFiltered) { topRatedItems() }
        }
        val discoverDeferred = async { discoverItems(genres) }
        val appleTVDeferred = async { appleTVItems(genres) }
        val netflixDeferred = async { netflixItems(genres) }
        val amazonPrimeDeferred = async { amazonPrimeItems(genres) }
        val googlePlayDeferred = async { googlePlayItems(genres) }

        val fetchedGenres = genresDeferred.await()

        Feed(
            feedWrapper = listOf(
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
            ),
            fetchedGenres,
        )
    }

    protected fun createFeedWrappers(itemsBySortType: Map<SortType, List<T>>, genres: List<Genre>): Feed {
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
        val feedWrapper = sortTypes.mapNotNull { (sortType, resId) ->
            itemsBySortType[sortType]?.let { feeds ->
                FeedWrapper(feeds, resId, sortType)
            }
        }
        return Feed(feedWrapper, genres)
    }

    protected fun Array<Int>.toQueryString(): String? = if (isEmpty()) null else joinToString(",")

    private suspend fun fetchUnfilteredItems(isFiltered: Boolean, fetch: suspend () -> List<T>): List<T> =
        if (isFiltered) emptyList() else fetch()
}
