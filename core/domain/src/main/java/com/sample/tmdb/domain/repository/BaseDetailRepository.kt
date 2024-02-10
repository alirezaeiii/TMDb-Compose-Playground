package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TMDbItemDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseDetailRepository<T : TMDbItemDetails>(
    context: Context,
    ioDispatcher: CoroutineDispatcher
) : BaseRepository<DetailWrapper<T>>(context, ioDispatcher) {

    protected abstract suspend fun getDetails(id: Int): T

    protected abstract suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>>

    protected abstract suspend fun getImages(id: Int): List<TMDbImage>

    override suspend fun getSuccessResult(id: Any?): DetailWrapper<T> {
        val detailsDeferred: Deferred<T>
        val creditDeferred: Deferred<Pair<List<Cast>, List<Crew>>>
        val imageDeferred: Deferred<List<TMDbImage>>
        coroutineScope {
            detailsDeferred = async { getDetails(id as Int) }
            creditDeferred = async { getCredit(id as Int) }
            imageDeferred= async { getImages(id as Int) }
        }
        val details = detailsDeferred.await()
        val creditWrapper = creditDeferred.await()
        val images = imageDeferred.await()

        return DetailWrapper(creditWrapper.first, creditWrapper.second, details, images)
    }
}