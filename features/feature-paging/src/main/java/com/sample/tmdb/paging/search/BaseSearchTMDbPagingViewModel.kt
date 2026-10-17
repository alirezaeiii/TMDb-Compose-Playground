package com.sample.tmdb.paging.search

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.sample.tmdb.common.base.BaseSearchPagingViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.domain.repository.BasePagingRepository
import kotlinx.coroutines.flow.Flow

open class BaseSearchTMDbPagingViewModel<T : TMDbItem>(
    private val repository: BasePagingRepository<T>,
    savedStateHandle: SavedStateHandle,
) : BaseSearchPagingViewModel<T>(savedStateHandle) {

    override fun fetchResultStream(query: String): Flow<PagingData<T>> = repository.fetchResultStream(query)

    override fun onItemClick(item: T) {
        emitEvent(PagingUiEvent.Navigate(item.detailNavKey))
    }
}
