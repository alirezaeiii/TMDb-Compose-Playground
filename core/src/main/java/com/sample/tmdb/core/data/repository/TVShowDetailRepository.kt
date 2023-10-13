package com.sample.tmdb.core.data.repository

import android.content.Context
import com.sample.tmdb.core.data.network.TVShowService
import com.sample.tmdb.core.data.response.NetworkCreditWrapper
import com.sample.tmdb.core.data.response.asDomainModel
import com.sample.tmdb.core.di.IoDispatcher
import com.sample.tmdb.core.domain.repository.BaseDetailRepository
import com.sample.tmdb.core.domain.model.TvDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowDetailRepository @Inject constructor(
    private val tvShowApi: TVShowService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<TvDetails>(context, ioDispatcher) {

    override suspend fun getDetails(id: Int): TvDetails =
        tvShowApi.fetchTvDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): NetworkCreditWrapper = tvShowApi.tvCredit(id)
}