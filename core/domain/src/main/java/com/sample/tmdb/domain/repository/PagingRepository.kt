package com.sample.tmdb.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.sample.tmdb.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface PagingRepository<T : Any> {

    @OptIn(ExperimentalPagingApi::class)
    fun fetchResultStream(query: String? = null, id: Int? = null, type: SortType? = null): Flow<PagingData<T>>
}
