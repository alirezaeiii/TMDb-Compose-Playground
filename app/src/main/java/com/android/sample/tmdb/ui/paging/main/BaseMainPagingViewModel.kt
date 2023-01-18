package com.android.sample.tmdb.ui.paging.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.sample.tmdb.domain.BasePagingRepository
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.paging.BasePagingViewModel
import kotlinx.coroutines.flow.Flow

open class BaseMainPagingViewModel<T : TMDbItem>(repository: BasePagingRepository<T>) :
    BasePagingViewModel<T>() {

    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream().cachedIn(viewModelScope)
}