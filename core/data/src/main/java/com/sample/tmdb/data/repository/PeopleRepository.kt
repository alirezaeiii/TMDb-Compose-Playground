package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.People
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    private val personApi: PersonService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<People, Nothing>(context, ioDispatcher) {
    override suspend fun getSuccessResult(isRefreshing: Boolean, id: Nothing?): People =
        personApi.getTrendingPeople().asDomainModel()
}