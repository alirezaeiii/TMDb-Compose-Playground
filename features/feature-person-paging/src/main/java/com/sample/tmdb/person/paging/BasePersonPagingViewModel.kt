package com.sample.tmdb.person.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.navigation.TMDbNavKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BasePersonPagingViewModel : ViewModel() {

    abstract val pagingDataFlow: Flow<PagingData<TrendingPerson>>

    private val _uiEvent = MutableSharedFlow<PagingPersonUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    protected fun emitEvent(event: PagingPersonUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    fun onItemClick(person: TrendingPerson) {
        emitEvent(PagingPersonUiEvent.Navigate(TMDbNavKey.Person(person.id)))
    }

    fun onNavigateUp() {
        emitEvent(PagingPersonUiEvent.NavigateUp)
    }
}
