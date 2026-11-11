package com.sample.tmdb.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import androidx.paging.map
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.repository.PagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BasePagingRepository<T : Any, E : Any> : PagingRepository<T> {

    @OptIn(ExperimentalPagingApi::class)
    protected abstract fun createRemoteMediator(
        query: String? = null,
        id: Int? = null,
        type: SortType? = null,
    ): RemoteMediator<Int, E>

    protected abstract fun createPagingSource(
        query: String? = null,
        id: Int? = null,
        type: SortType? = null,
    ): PagingSource<Int, E>

    protected abstract fun mapEntityToDomain(entity: E): T

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchResultStream(query: String?, id: Int?, type: SortType?): Flow<PagingData<T>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        remoteMediator = createRemoteMediator(query, id, type),
        pagingSourceFactory = { createPagingSource(query, id, type) },
    ).flow.map { pagingData ->
        pagingData.map { entity ->
            mapEntityToDomain(entity)
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}