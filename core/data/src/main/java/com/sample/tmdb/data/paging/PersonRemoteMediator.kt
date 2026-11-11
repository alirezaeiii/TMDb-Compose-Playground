package com.sample.tmdb.data.paging

import android.content.Context
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asPagingDatabaseModel
import com.sample.tmdb.data.source.entity.PersonPagingEntity
import com.sample.tmdb.data.source.entity.PersonRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase

class PersonRemoteMediator(
    private val database: TMDbDatabase,
    private val personApi: PersonService,
    context: Context,
) : BaseRemoteMediator<PersonPagingEntity, Int, PersonRemoteKeyEntity>(database, context) {

    override suspend fun fetchItems(page: Int): List<PersonPagingEntity> =
        personApi.getTrendingPeople(page).results.asPagingDatabaseModel(page)

    override fun getRemoteKeyId(item: PersonPagingEntity): Int = item.id

    override suspend fun clearLocalData() {
        database.personRemoteKeyDao.deleteAllRemoteKeys()
        database.personPagingDao.deleteAll()
    }

    override suspend fun saveLocalData(keys: List<PersonRemoteKeyEntity>, items: List<PersonPagingEntity>) {
        database.personRemoteKeyDao.insertRemoteKeys(keys)
        database.personPagingDao.insertAll(items)
    }

    override suspend fun getRemoteKey(id: Int): PersonRemoteKeyEntity? = database.personRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: PersonRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: PersonRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<PersonPagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<PersonRemoteKeyEntity> = items.map { person ->
        PersonRemoteKeyEntity(
            id = person.id,
            prevKey = prevKey,
            nextKey = nextKey,
        )
    }
}
