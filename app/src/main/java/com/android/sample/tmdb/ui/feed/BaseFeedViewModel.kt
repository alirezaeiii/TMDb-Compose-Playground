package com.android.sample.tmdb.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sample.tmdb.data.FeedWrapper
import com.android.sample.tmdb.data.TMDbItem
import com.android.sample.tmdb.domain.BaseFeedRepository
import com.android.sample.tmdb.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseFeedViewModel<T: TMDbItem>(
    private val repository: BaseFeedRepository<T>,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<List<FeedWrapper<T>>>>(Resource.Loading)
    val stateFlow: StateFlow<Resource<List<FeedWrapper<T>>>>
        get() = _stateFlow

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.result.collect {
                _stateFlow.tryEmit(it)
            }
        }
    }
}