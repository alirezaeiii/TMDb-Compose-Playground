package com.sample.tmdb.domain.repository

import androidx.paging.PagingData
import com.sample.tmdb.common.model.TrendingPerson
import kotlinx.coroutines.flow.Flow

interface PersonPagingRepository {
    fun fetchResultStream(query: String? = null): Flow<PagingData<TrendingPerson>>
}
