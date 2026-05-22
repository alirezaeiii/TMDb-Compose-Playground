package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TMDbItemDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseDetailRepository<T : TMDbItemDetails>(context: Context, ioDispatcher: CoroutineDispatcher) :
    BaseRepository<DetailWrapper, Int>(context, ioDispatcher) {
    protected abstract suspend fun getDetails(id: Int): T

    protected abstract suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>>

    protected abstract suspend fun getImages(id: Int): List<TMDbImage>

    protected abstract suspend fun getSimilarItems(id: Int): List<TMDbItem>

    override suspend fun getSuccessResult(isRefreshing: Boolean, id: Int?): DetailWrapper = coroutineScope {
        val detailsDeferred = async { getDetails(id!!) }
        val creditDeferred = async { getCredit(id!!) }
        val imageDeferred = async { getImages(id!!) }
        val similarDeferred = async { getSimilarItems(id!!) }

        val creditWrapper = creditDeferred.await()

        DetailWrapper(
            creditWrapper.first,
            creditWrapper.second,
            detailsDeferred.await(),
            imageDeferred.await(),
            similarDeferred.await(),
        )
    }
}
