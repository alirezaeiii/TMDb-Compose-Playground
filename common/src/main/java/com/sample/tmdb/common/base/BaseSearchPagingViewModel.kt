package com.sample.tmdb.common.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

abstract class BaseSearchPagingViewModel<T : Any>(
    private val savedStateHandle: SavedStateHandle,
) : BasePagingViewModel<T>() {

    protected abstract fun fetchResultStream(query: String): Flow<PagingData<T>>

    @OptIn(ExperimentalCoroutinesApi::class)
    override val pagingDataFlow: Flow<PagingData<T>> =
        savedStateHandle
            .getLiveData<String>(KEY_QUERY)
            .asFlow()
            .flatMapLatest {
                fetchResultStream(it)
            }.cachedIn(viewModelScope)

    fun showResult(query: String) {
        if (!shouldShowResult(query)) return
        savedStateHandle[KEY_QUERY] = query
    }

    private fun shouldShowResult(query: String): Boolean = savedStateHandle.get<String>(KEY_QUERY) != query

    companion object {
        const val KEY_QUERY = "query"
    }
}
