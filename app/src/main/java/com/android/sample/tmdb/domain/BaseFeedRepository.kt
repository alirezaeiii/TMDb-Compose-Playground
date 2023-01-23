package com.android.sample.tmdb.domain

import android.content.Context
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.FeedWrapper
import com.android.sample.tmdb.domain.model.SortType
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseFeedRepository<T : TMDbItem>(
    context: Context,
    ioDispatcher: CoroutineDispatcher
) : BaseRepository<List<FeedWrapper<T>>>(context, ioDispatcher) {

    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override suspend fun getSuccessResult(id: Any?): Resource<List<FeedWrapper<T>>> {
        val trendingDeferred: Deferred<List<T>>
        val nowPlayingDeferred: Deferred<List<T>>
        val popularDeferred: Deferred<List<T>>
        val latestDeferred: Deferred<List<T>>
        val topRatedDeferred: Deferred<List<T>>
        coroutineScope {
            trendingDeferred = async { trendingItems() }
            nowPlayingDeferred = async { nowPlayingItems() }
            popularDeferred = async { popularItems() }
            latestDeferred = async { latestItems() }
            topRatedDeferred = async { topRatedItems() }
        }
        return Resource.Success(
            listOf(
                FeedWrapper(
                    trendingDeferred.await(),
                    R.string.text_trending,
                    SortType.TRENDING
                ),
                FeedWrapper(
                    popularDeferred.await(),
                    R.string.text_popular,
                    SortType.MOST_POPULAR
                ),
                FeedWrapper(
                    nowPlayingDeferred.await(),
                    getNowPlayingResId(),
                    SortType.NOW_PLAYING
                ),
                FeedWrapper(
                    latestDeferred.await(),
                    getLatestResId(),
                    SortType.UPCOMING
                ),
                FeedWrapper(
                    topRatedDeferred.await(),
                    R.string.text_highest_rate,
                    SortType.HIGHEST_RATED
                )
            )
        )
    }
}