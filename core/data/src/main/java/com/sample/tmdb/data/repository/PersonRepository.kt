package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.source.entity.asDatabaseModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.PersonDao
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseCacheRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class PersonRepository @Inject constructor(
    private val personApi: PersonService,
    private val personDao: PersonDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseCacheRepository<Person, Int, Int>(context, ioDispatcher) {

    override suspend fun query(queryParam: Int?): Person? = personDao.getPerson(queryParam!!)?.asDomainModel()

    override suspend fun fetch(fetchParam: Int?): Person = personApi.getPerson(fetchParam!!).asDomainModel()

    override suspend fun saveFetchResult(item: Person) {
        personDao.insert(item.asDatabaseModel())
    }
}
