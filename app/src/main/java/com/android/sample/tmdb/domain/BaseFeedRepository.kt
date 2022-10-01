package com.android.sample.tmdb.domain

import android.content.Context
import com.android.sample.tmdb.R
import com.android.sample.tmdb.data.FeedWrapper
import com.android.sample.tmdb.data.ItemWrapper
import com.android.sample.tmdb.data.TMDbItem
import com.android.sample.tmdb.utils.Resource
import com.android.sample.tmdb.utils.SortType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseFeedRepository<T : TMDbItem>(
    private val context: Context,
    ioDispatcher: CoroutineDispatcher
) {

    protected abstract suspend fun popularItems(): ItemWrapper<T>

    protected abstract suspend fun nowPlayingItems(): ItemWrapper<T>

    protected abstract suspend fun latestItems(): ItemWrapper<T>

    protected abstract suspend fun topRatedItems(): ItemWrapper<T>

    protected abstract suspend fun trendingItems(): ItemWrapper<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    val result = flow {
        emit(Resource.Loading)
        try {
            coroutineScope {
                val trendingDeferred: Deferred<ItemWrapper<T>> = async { trendingItems() }
                val nowPlayingDeferred: Deferred<ItemWrapper<T>> = async { nowPlayingItems() }
                val popularDeferred: Deferred<ItemWrapper<T>> = async { popularItems() }
                val latestDeferred: Deferred<ItemWrapper<T>> = async { latestItems() }
                val topRatedDeferred: Deferred<ItemWrapper<T>> = async { topRatedItems() }

                emit(
                    Resource.Success(
                        listOf(
                            FeedWrapper(
                                trendingDeferred.await().items,
                                R.string.text_trending,
                                SortType.TRENDING
                            ),
                            FeedWrapper(
                                popularDeferred.await().items,
                                R.string.text_popular,
                                SortType.MOST_POPULAR
                            ),
                            FeedWrapper(
                                nowPlayingDeferred.await().items,
                                getNowPlayingResId(),
                                SortType.NOW_PLAYING
                            ),
                            FeedWrapper(
                                latestDeferred.await().items,
                                getLatestResId(),
                                SortType.UPCOMING
                            ),
                            FeedWrapper(
                                topRatedDeferred.await().items,
                                R.string.text_highest_rate,
                                SortType.HIGHEST_RATED
                            )
                        )
                    )
                )
            }
        } catch (t: Throwable) {
            emit(Resource.Error(context.getString(R.string.failed_loading_msg)))
        }

    }.flowOn(ioDispatcher)
}