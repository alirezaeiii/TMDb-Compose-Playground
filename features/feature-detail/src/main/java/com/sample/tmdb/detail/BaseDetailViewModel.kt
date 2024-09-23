package com.sample.tmdb.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.base.TMDbViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseDetailViewModel<T : TMDbItemDetails, R : TMDbItem>(
    private val bookmarkRepository: BookmarkDetailsRepository<R>,
    repository: BaseDetailRepository<T>,
    savedStateHandle: SavedStateHandle,
) : TMDbViewModel<DetailWrapper>(
        repository,
        savedStateHandle[MainDestinations.TMDB_ID_KEY],
    ) {
    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean>
        get() = _isBookmarked

    fun addBookmark(item: R) =
        viewModelScope.launch {
            bookmarkRepository.addBookmark(item)
            isBookmarked(item.id)
        }

    fun removeBookmark(id: Int) =
        viewModelScope.launch {
            bookmarkRepository.deleteBookmark(id)
            isBookmarked(id)
        }

    fun isBookmarked(id: Int) =
        viewModelScope.launch {
            _isBookmarked.emit(bookmarkRepository.isBookmarked(id))
        }
}
