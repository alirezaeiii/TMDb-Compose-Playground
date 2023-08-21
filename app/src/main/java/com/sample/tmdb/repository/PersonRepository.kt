package com.sample.tmdb.repository

import android.content.Context
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.di.IoDispatcher
import com.sample.tmdb.domain.BaseRepository
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personApi: PersonService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<Person>(context, ioDispatcher) {

    override suspend fun getSuccessResult(id: Any?): Resource<Person> =
        Resource.Success(personApi.getPerson(id as String).asDomainModel())
}