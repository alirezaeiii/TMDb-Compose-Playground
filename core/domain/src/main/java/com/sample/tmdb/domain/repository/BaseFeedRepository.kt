package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseFeedRepository<T : TMDbItem>(context: Context, ioDispatcher: CoroutineDispatcher) :
    BaseCacheRepository<List<FeedWrapper>, Unit, Unit>(context, ioDispatcher) {

    private var _selectedGenres: Array<Int> = emptyArray()
    val selectedGenres: Array<Int> get() = _selectedGenres

    fun setSelectedGenres(genres: Array<Int>) {
        _selectedGenres = genres
    }

    protected abstract suspend fun popularItems(genres: Array<Int>): List<T>

    protected abstract suspend fun nowPlayingItems(genres: Array<Int>): List<T>

    protected abstract suspend fun latestItems(genres: Array<Int>): List<T>

    protected abstract suspend fun topRatedItems(genres: Array<Int>): List<T>

    protected abstract suspend fun trendingItems(genres: Array<Int>): List<T>

    protected abstract suspend fun discoverItems(genres: Array<Int>): List<T>

    protected abstract suspend fun appleTVItems(genres: Array<Int>): List<T>

    protected abstract suspend fun netflixItems(genres: Array<Int>): List<T>

    protected abstract suspend fun amazonPrimeItems(genres: Array<Int>): List<T>

    protected abstract suspend fun googlePlayItems(genres: Array<Int>): List<T>

    abstract suspend fun getGenres(): List<Genre>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override suspend fun fetch(fetchParam: Unit?): List<FeedWrapper> = coroutineScope {
        val genres = selectedGenres
        val genresDeferred = async { getGenres() }
        val trendingDeferred = async { trendingItems(genres) }
        val nowPlayingDeferred = async { nowPlayingItems(genres) }
        val popularDeferred = async { popularItems(genres) }
        val latestDeferred = async { latestItems(genres) }
        val topRatedDeferred = async { topRatedItems(genres) }
        val discoverDeferred = async { discoverItems(genres) }
        val appleTVDeferred = async { appleTVItems(genres) }
        val netflixDeferred = async { netflixItems(genres) }
        val amazonPrimeDeferred = async { amazonPrimeItems(genres) }
        val googlePlayDeferred = async { googlePlayItems(genres) }

        val fetchedGenres = genresDeferred.await()

        listOf(
            FeedWrapper(trendingDeferred.await(), R.string.text_trending, SortType.TRENDING, fetchedGenres),
            FeedWrapper(popularDeferred.await(), R.string.text_popular, SortType.MOST_POPULAR, fetchedGenres),
            FeedWrapper(nowPlayingDeferred.await(), getNowPlayingResId(), SortType.NOW_PLAYING, fetchedGenres),
            FeedWrapper(discoverDeferred.await(), R.string.text_discover, SortType.DISCOVER, fetchedGenres),
            FeedWrapper(latestDeferred.await(), getLatestResId(), SortType.UPCOMING, fetchedGenres),
            FeedWrapper(topRatedDeferred.await(), R.string.text_highest_rate, SortType.HIGHEST_RATED, fetchedGenres),
            FeedWrapper(appleTVDeferred.await(), R.string.text_apple_tv, SortType.APPLE_TV, fetchedGenres),
            FeedWrapper(netflixDeferred.await(), R.string.text_netflix, SortType.NETFLIX, fetchedGenres),
            FeedWrapper(amazonPrimeDeferred.await(), R.string.text_amazon_prime, SortType.AMAZON_PRIME, fetchedGenres),
            FeedWrapper(googlePlayDeferred.await(), R.string.text_google_play, SortType.GOOGLE_PLAY, fetchedGenres),
        )
    }

    protected fun createFeedWrappers(itemsBySortType: Map<SortType, List<T>>, genres: List<Genre>): List<FeedWrapper> {
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
                FeedWrapper(feeds, resId, sortType, genres)
            }
        }
    }
}
