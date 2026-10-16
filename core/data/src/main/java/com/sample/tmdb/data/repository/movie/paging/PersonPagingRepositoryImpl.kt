package com.sample.tmdb.data.repository.movie.paging

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.paging.PersonPagingSource
import com.sample.tmdb.domain.repository.PersonPagingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class PersonPagingRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val personApi: PersonService,
) : PersonPagingRepository {

    override fun fetchResultStream(query: String?): Flow<PagingData<TrendingPerson>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        pagingSourceFactory = { PersonPagingSource(personApi, context, query) },
    ).flow

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
