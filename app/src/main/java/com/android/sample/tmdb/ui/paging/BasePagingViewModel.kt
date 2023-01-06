package com.android.sample.tmdb.ui.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.sample.tmdb.domain.BasePagingRepository
import com.android.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.flow.Flow

open class BasePagingViewModel<T : TMDbItem>(repository: BasePagingRepository<T>) :
    ViewModel() {

    val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream.cachedIn(viewModelScope)
}