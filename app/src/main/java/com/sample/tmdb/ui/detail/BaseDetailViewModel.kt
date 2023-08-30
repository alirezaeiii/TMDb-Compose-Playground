package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkItemDetailsRepository
import com.sample.tmdb.ui.BaseRefreshViewModel
import com.sample.tmdb.ui.MainDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseDetailViewModel<T : TMDbItemDetails, R: TMDbItem>(
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

    fun addBookmark(movie: R) = viewModelScope.launch {
        bookmarkRepository.addBookmark(movie)
        isBookmarked(movie.id)
    }

    fun removeBookmark(id: Int) = viewModelScope.launch {
        bookmarkRepository.deleteBookmark(id)
        isBookmarked(id)
    }

    fun isBookmarked(id: Int) = viewModelScope.launch {
        _isBookmarked.emit(bookmarkRepository.isBookmarked(id))
    }
}