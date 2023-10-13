package com.sample.tmdb.ui.paging.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.ui.paging.BasePagingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

open class BaseSearchPagingViewModel<T : TMDbItem>(
    repository: BasePagingRepository<T>,
    private val savedStateHandle: SavedStateHandle
) : BasePagingViewModel<T>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val pagingDataFlow: Flow<PagingData<T>> =
        savedStateHandle.getLiveData<String>(KEY_QUERY).asFlow().flatMapLatest {
            repository.fetchResultStream(it)
        }.cachedIn(viewModelScope)


    fun showResult(query: String) {
        if (!shouldShowResult(query)) return
        savedStateHandle[KEY_QUERY] = query
    }

    private fun shouldShowResult(query: String): Boolean =
        savedStateHandle.get<String>(KEY_QUERY) != query

    companion object {
        private const val KEY_QUERY = "query"
    }
}