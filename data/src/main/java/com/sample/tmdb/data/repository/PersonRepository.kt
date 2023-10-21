package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.domain.annotations.IoDispatcher
import com.sample.tmdb.domain.model.Person
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personApi: PersonService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<Person>(context, ioDispatcher) {

    override suspend fun getSuccessResult(id: Any?): Person =
        personApi.getPerson(id as String).asDomainModel()
}