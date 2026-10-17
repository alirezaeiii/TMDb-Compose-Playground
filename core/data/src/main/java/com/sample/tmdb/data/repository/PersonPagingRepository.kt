package com.sample.tmdb.data.repository

import android.content.Context
import androidx.paging.PagingSource
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.paging.PersonPagingSource
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.SortType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val personApi: PersonService,
) : BasePagingRepository<TrendingPerson>() {

    override fun createPagingSource(query: String?, id: Int?, type: SortType?): PagingSource<Int, TrendingPerson> =
        PersonPagingSource(personApi, context, query)
}
