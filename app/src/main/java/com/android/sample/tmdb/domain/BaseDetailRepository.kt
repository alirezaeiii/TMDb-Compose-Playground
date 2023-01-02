package com.android.sample.tmdb.domain

import android.content.Context
import com.android.sample.tmdb.data.response.NetworkCreditWrapper
import com.android.sample.tmdb.data.response.asCastDomainModel
import com.android.sample.tmdb.data.response.asCrewDomainModel
import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseDetailRepository(
    context: Context,
    ioDispatcher: CoroutineDispatcher
) : BaseRepository<DetailWrapper>(context, ioDispatcher) {

    protected abstract suspend fun getCredit(id: Int): NetworkCreditWrapper

    override suspend fun successResult(id: Any?): Resource<DetailWrapper> {
        val creditDeferred: Deferred<NetworkCreditWrapper>
        coroutineScope {
            creditDeferred = async { getCredit(id as Int) }
        }
        val networkCreditWrapper = creditDeferred.await()
        val cast = networkCreditWrapper.cast.asCastDomainModel()
        val crew = networkCreditWrapper.crew.asCrewDomainModel()

        return Resource.Success(DetailWrapper(cast, crew))
    }
}