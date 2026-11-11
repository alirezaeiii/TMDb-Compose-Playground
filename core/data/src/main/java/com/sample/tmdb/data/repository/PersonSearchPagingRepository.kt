package com.sample.tmdb.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.paging.PersonSearchRemoteMediator
import com.sample.tmdb.data.source.entity.PersonSearchPagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TrendingPerson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonSearchPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val personApi: PersonService,
    @ApplicationContext private val context: Context
) : BasePagingRepository<TrendingPerson, PersonSearchPagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, PersonSearchPagingEntity> = PersonSearchRemoteMediator(database, personApi, query!!, context)

    override fun createPagingSource(
        query: String?,
        id: Int?,
        type: SortType?,
    ): PagingSource<Int, PersonSearchPagingEntity> = database.personSearchPagingDao.getPersons(query!!)

    override fun mapEntityToDomain(entity: PersonSearchPagingEntity): TrendingPerson = entity.asDomainModel()
}
