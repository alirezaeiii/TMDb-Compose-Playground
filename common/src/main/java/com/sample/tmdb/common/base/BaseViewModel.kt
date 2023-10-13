package com.sample.tmdb.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<T>(
    private val repository: BaseRepository<T>,
    private val id: Any? = null
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<T>>(Resource.Loading)
    val stateFlow: StateFlow<Resource<T>>
        get() = _stateFlow

    fun refresh() {
        viewModelScope.launch {
            repository.getResult(id).collect {
                _stateFlow.tryEmit(it)
            }
        }
    }
}