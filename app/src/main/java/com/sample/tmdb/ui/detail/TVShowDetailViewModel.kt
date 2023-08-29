package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TvDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkTVShowDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkTVShowDetailsRepository,
    repository: BaseDetailRepository<TvDetails>,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<TvDetails>(repository, savedStateHandle) {

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean>
        get() = _isBookmarked

    fun addBookmark(tvShow: TVShow) = viewModelScope.launch {
        bookmarkRepository.addBookmark(tvShow)
        isBookmarked(tvShow.id)
    }

    fun removeBookmark(id: Int) = viewModelScope.launch {
        bookmarkRepository.deleteBookmark(id)
        isBookmarked(id)
    }

    fun isBookmarked(id: Int) = viewModelScope.launch {
        _isBookmarked.emit(bookmarkRepository.isBookmarked(id))
    }
}