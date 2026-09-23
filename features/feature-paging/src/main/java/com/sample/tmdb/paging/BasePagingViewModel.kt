package com.sample.tmdb.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BasePagingViewModel<T : TMDbItem> : ViewModel() {
    abstract val pagingDataFlow: Flow<PagingData<T>>

    private val _uiEvent = MutableSharedFlow<PagingUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    protected fun emitEvent(event: PagingUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    open fun onItemClick(item: TMDbItem) {
        val route = when (item) {
            is Movie -> MovieDetail(item.id)
            is TVShow -> TvShowDetail(item.id)
            else -> throw RuntimeException("Invalid TMDb item type")
        }
        emitEvent(PagingUiEvent.Navigate(route))
    }

    open fun onSearchClick() {
    }

    fun onNavigateUp() {
        emitEvent(PagingUiEvent.NavigateUp)
    }
}
