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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseFeedRepository<T : TMDbItem>(
    private val context: Context,
    ioDispatcher: CoroutineDispatcher
): BaseRepository<List<FeedWrapper<T>>>() {

    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override val result = flow {
        emit(Resource.Loading)
        try {
            coroutineScope {
                val trendingDeferred: Deferred<List<T>> = async { trendingItems() }
                val nowPlayingDeferred: Deferred<List<T>> = async { nowPlayingItems() }
                val popularDeferred: Deferred<List<T>> = async { popularItems() }
                val latestDeferred: Deferred<List<T>> = async { latestItems() }
                val topRatedDeferred: Deferred<List<T>> = async { topRatedItems() }

                emit(
                    Resource.Success(
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
                )
            }
        } catch (t: Throwable) {
            emit(Resource.Error(context.getString(R.string.failed_loading_msg)))
        }

    }.flowOn(ioDispatcher)
}