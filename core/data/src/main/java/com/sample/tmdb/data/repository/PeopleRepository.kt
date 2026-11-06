package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.entity.asFeedDatabaseModel
import com.sample.tmdb.data.source.local.PersonFeedDao
import com.sample.tmdb.domain.model.People
import com.sample.tmdb.domain.repository.BaseCacheRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class PeopleRepository @Inject constructor(
    private val personApi: PersonService,
    private val personFeedDao: PersonFeedDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseCacheRepository<People, Unit, Unit>(context, ioDispatcher) {

    override suspend fun query(queryParam: Unit?): People? {
        val dbEntities = personFeedDao.getFeed()
        if (dbEntities.isEmpty()) return null
        return People(result = dbEntities.asDomainModel())
    }

    override suspend fun fetch(fetchParam: Unit?): People = personApi.getTrendingPeople().asDomainModel()

    override suspend fun saveFetchResult(item: People) {
        val entities = item.result.mapIndexed { index, person ->
            person.asFeedDatabaseModel(index)
        }
        personFeedDao.deleteAndInsert(entities)
    }
}
