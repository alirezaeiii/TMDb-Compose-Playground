package com.sample.tmdb.domain.repository

import androidx.paging.PagingData
import com.sample.tmdb.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface PagingRepository<T : Any> {

    fun fetchResultStream(
        query: String? = null,
        id: Int? = null,
        type: SortType? = null,
        genres: String = "",
    ): Flow<PagingData<T>>
}
