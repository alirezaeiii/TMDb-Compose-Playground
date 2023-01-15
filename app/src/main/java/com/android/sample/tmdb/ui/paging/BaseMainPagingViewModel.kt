package com.android.sample.tmdb.ui.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.sample.tmdb.domain.BasePagingRepository
import com.android.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.flow.Flow

open class BaseMainPagingViewModel<T : TMDbItem>(private val repository: BasePagingRepository<T>) :
    BasePagingViewModel<T>() {

    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream().cachedIn(viewModelScope)
}