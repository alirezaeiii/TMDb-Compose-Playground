package com.sample.tmdb.data.paging

import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asSearchPagingDatabaseModel
import com.sample.tmdb.data.source.entity.PersonSearchPagingEntity
import com.sample.tmdb.data.source.entity.PersonSearchRemoteKeyEntity
import com.sample.tmdb.data.source.local.TMDbDatabase

class PersonSearchRemoteMediator(
    private val database: TMDbDatabase,
    private val personApi: PersonService,
    private val query: String,
) : BaseRemoteMediator<PersonSearchPagingEntity, String, PersonSearchRemoteKeyEntity>(database) {

    override suspend fun fetchItems(page: Int): List<PersonSearchPagingEntity> =
        personApi.getTrendingPeople(query, page).results.asSearchPagingDatabaseModel(query, page)

    override fun getRemoteKeyId(item: PersonSearchPagingEntity): String = item.queryStr

    override suspend fun clearLocalData() {
        database.personSearchRemoteKeyDao.deleteRemoteKey(query)
        database.personSearchPagingDao.deleteAll(query)
    }

    override suspend fun saveLocalData(keys: List<PersonSearchRemoteKeyEntity>, items: List<PersonSearchPagingEntity>) {
        database.personSearchRemoteKeyDao.insertRemoteKey(keys)
        database.personSearchPagingDao.insertAll(items)
    }

    override suspend fun getRemoteKey(id: String): PersonSearchRemoteKeyEntity? =
        database.personSearchRemoteKeyDao.getRemoteKey(id)

    override fun getPrevKey(remoteKey: PersonSearchRemoteKeyEntity): Int? = remoteKey.prevKey

    override fun getNextKey(remoteKey: PersonSearchRemoteKeyEntity): Int? = remoteKey.nextKey

    override fun createRemoteKeys(
        items: List<PersonSearchPagingEntity>,
        prevKey: Int?,
        nextKey: Int?,
    ): List<PersonSearchRemoteKeyEntity> = listOf(
        PersonSearchRemoteKeyEntity(
            queryStr = query,
            prevKey = prevKey,
            nextKey = nextKey,
        ),
    )
}
