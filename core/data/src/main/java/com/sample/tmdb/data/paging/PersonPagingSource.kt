package com.sample.tmdb.data.paging

import android.content.Context
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.navigation.model.TrendingPerson

class PersonPagingSource(private val personApi: PersonService, context: Context, private val query: String? = null) :
    BasePagingSource<TrendingPerson>(context) {

    override suspend fun fetchItems(page: Int): List<TrendingPerson> = (
        query?.let {
            personApi.getTrendingPeople(it, page)
        } ?: personApi.getTrendingPeople(page)
        ).results.asDomainModel()
}
