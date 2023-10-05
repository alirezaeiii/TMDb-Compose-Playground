package com.sample.tmdb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.tmdb.data.paging.BasePagingSource
import com.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.flow.Flow

abstract class BasePagingRepository<T : TMDbItem> {

    protected abstract fun pagingSource(query: String?): BasePagingSource<T>

    fun fetchResultStream(query: String?= null): Flow<PagingData<T>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        pagingSourceFactory = { pagingSource(query) }
    ).flow

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}