package com.sample.tmdb.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sample.tmdb.common.ui.PagingUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BasePagingViewModel<T : Any> : ViewModel() {
    abstract val pagingDataFlow: Flow<PagingData<T>>

    private val _uiEvent = MutableSharedFlow<PagingUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    protected fun emitEvent(event: PagingUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    open fun onItemClick(item: T) {}

    fun onNavigateUp() {
        emitEvent(PagingUiEvent.NavigateUp)
    }
}
