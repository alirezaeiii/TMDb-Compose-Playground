package com.android.sample.tmdb.domain

import android.content.Context
import com.android.sample.tmdb.R
import com.android.sample.tmdb.data.response.NetworkCreditWrapper
import com.android.sample.tmdb.data.response.asCastDomainModel
import com.android.sample.tmdb.data.response.asCrewDomainModel
import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseDetailRepository(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : BaseRepository<DetailWrapper>() {

    protected abstract suspend fun getCredit(id: Int): NetworkCreditWrapper

    override fun getResult(id: Any?) = flow {
        emit(Resource.Loading)
        try {
            coroutineScope {
                val creditDeferred: Deferred<NetworkCreditWrapper> = async { getCredit(id as Int) }
                val networkCreditWrapper = creditDeferred.await()

                val cast = networkCreditWrapper.cast.asCastDomainModel()
                val crew = networkCreditWrapper.crew.asCrewDomainModel()

                emit(Resource.Success(DetailWrapper(cast, crew)))
            }
        } catch (t: Throwable) {
            emit(Resource.Error(context.getString(R.string.failed_loading_msg)))
        }

    }.flowOn(ioDispatcher)
}