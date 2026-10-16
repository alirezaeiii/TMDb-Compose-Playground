package com.sample.tmdb.paging.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.SortType
import kotlinx.coroutines.flow.Flow

abstract class BaseMainPagingViewModel<T : TMDbItem>(
    repository: BasePagingRepository<T>,
    id: Int? = null,
    type: SortType,
) : BasePagingViewModel<T>() {
    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream(id = id, type = type).cachedIn(viewModelScope)

    abstract fun onSearchClick()

    override fun onItemClick(item: T) {
        emitEvent(PagingUiEvent.Navigate(item.detailNavKey))
    }
}
