package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.data.network.TVShowService
import com.android.sample.tmdb.data.response.NetworkCreditWrapper
import com.android.sample.tmdb.data.response.asDomainModel
import com.android.sample.tmdb.di.IoDispatcher
import com.android.sample.tmdb.domain.BaseDetailRepository
import com.android.sample.tmdb.domain.model.TvDetails
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowDetailRepository @Inject constructor(
    private val tvShowApi: TVShowService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<TvDetails>(context, ioDispatcher) {

    override suspend fun getDetails(id: Int): TvDetails =
        tvShowApi.fetchTvDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): NetworkCreditWrapper = tvShowApi.tvCredit(id)
}