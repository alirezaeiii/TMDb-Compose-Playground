package com.sample.tmdb.paging.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.BasePagingViewModel
import kotlinx.coroutines.flow.Flow

open class BaseMainPagingViewModel<T : TMDbItem>(repository: BasePagingRepository<T>) :
    BasePagingViewModel<T>() {

    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream().cachedIn(viewModelScope)
}