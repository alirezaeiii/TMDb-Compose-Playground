package com.sample.tmdb.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.base.BaseRefreshViewModel
import com.sample.tmdb.common.model.MainDestinations
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.core.domain.model.DetailWrapper
import com.sample.tmdb.core.domain.model.TMDbItemDetails
import com.sample.tmdb.core.domain.repository.BaseDetailRepository
import com.sample.tmdb.core.domain.repository.BookmarkItemDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseDetailViewModel<T : TMDbItemDetails, R : TMDbItem>(
    private val bookmarkRepository: BookmarkItemDetailsRepository<R>,
    repository: BaseDetailRepository<T>,
    savedStateHandle: SavedStateHandle
) : BaseRefreshViewModel<DetailWrapper<T>>(
    repository,
    savedStateHandle[MainDestinations.TMDB_ID_KEY]
) {
    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean>
        get() = _isBookmarked

    fun addBookmark(item: R) = viewModelScope.launch {
        bookmarkRepository.addBookmark(item)
        isBookmarked(item.id)
    }

    fun removeBookmark(id: Int) = viewModelScope.launch {
        bookmarkRepository.deleteBookmark(id)
        isBookmarked(id)
    }

    fun isBookmarked(id: Int) = viewModelScope.launch {
        _isBookmarked.emit(bookmarkRepository.isBookmarked(id))
    }
}