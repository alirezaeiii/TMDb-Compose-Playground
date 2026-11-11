package com.sample.tmdb.data.paging

import android.content.Context
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asPagingDatabaseModel
import com.sample.tmdb.data.source.entity.PersonPagingEntity
import com.sample.tmdb.domain.paging.BasePagingSource

class PersonPagingSource(private val personApi: PersonService, context: Context, private val query: String? = null) :
    BasePagingSource<PersonPagingEntity>(context) {

    override suspend fun fetchItems(page: Int): List<PersonPagingEntity> = (
        query?.let {
            personApi.getTrendingPeople(it, page)
        } ?: personApi.getTrendingPeople(page)
        ).results.asPagingDatabaseModel(page)
}
