package com.sample.tmdb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.navigation.SortType
import kotlinx.coroutines.flow.Flow

abstract class BasePagingRepository<T : TMDbItem> {
    protected abstract fun pagingSource(query: String?, id: Int?, type: SortType?): BasePagingSource<T>

    fun fetchResultStream(query: String? = null, id: Int? = null, type: SortType? = null): Flow<PagingData<T>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        pagingSourceFactory = { pagingSource(query, id, type) },
    ).flow

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
