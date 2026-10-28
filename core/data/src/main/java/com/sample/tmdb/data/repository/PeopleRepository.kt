package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.domain.model.People
import com.sample.tmdb.domain.repository.BaseRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class PeopleRepository @Inject constructor(
    private val personApi: PersonService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<People, Nothing>(context, ioDispatcher) {
    override suspend fun getSuccessResult(isRefreshing: Boolean, id: Nothing?): People =
        personApi.getTrendingPeople().asDomainModel()
}
