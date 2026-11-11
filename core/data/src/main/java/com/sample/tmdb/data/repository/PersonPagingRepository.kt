package com.sample.tmdb.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.paging.PersonRemoteMediator
import com.sample.tmdb.data.source.entity.PersonPagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TrendingPerson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val personApi: PersonService,
    @ApplicationContext private val context: Context,
) : BasePagingRepository<TrendingPerson, PersonPagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, PersonPagingEntity> = PersonRemoteMediator(database, personApi, context)

    override fun createPagingSource(query: String?, id: Int?, type: SortType?): PagingSource<Int, PersonPagingEntity> =
        database.personPagingDao.getPagingPersons()

    override fun mapEntityToDomain(entity: PersonPagingEntity): TrendingPerson = entity.asDomainModel()
}
