package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.SortType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseFeedRepository<T : TMDbItem>(context: Context, ioDispatcher: CoroutineDispatcher) :
    BaseRepository<List<FeedWrapper>, Nothing>(context, ioDispatcher) {
    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract suspend fun discoverItems(): List<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override suspend fun getSuccessResult(isRefreshing: Boolean, id: Nothing?): List<FeedWrapper> = coroutineScope {
        val trendingDeferred = async { trendingItems() }
        val nowPlayingDeferred = async { nowPlayingItems() }
        val popularDeferred = async { popularItems() }
        val latestDeferred = async { latestItems() }
        val topRatedDeferred = async { topRatedItems() }
        val discoverDeferred = async { discoverItems() }

        listOf(
            FeedWrapper(trendingDeferred.await(), R.string.text_trending, SortType.TRENDING),
            FeedWrapper(popularDeferred.await(), R.string.text_popular, SortType.MOST_POPULAR),
            FeedWrapper(nowPlayingDeferred.await(), getNowPlayingResId(), SortType.NOW_PLAYING),
            FeedWrapper(discoverDeferred.await(), R.string.text_discover, SortType.DISCOVER),
            FeedWrapper(latestDeferred.await(), getLatestResId(), SortType.UPCOMING),
            FeedWrapper(topRatedDeferred.await(), R.string.text_highest_rate, SortType.HIGHEST_RATED),
        )
    }
}
