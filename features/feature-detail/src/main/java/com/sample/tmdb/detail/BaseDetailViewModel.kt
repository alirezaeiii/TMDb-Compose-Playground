package com.sample.tmdb.detail

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.ui.Person
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseDetailViewModel<ItemDetails : TMDbItemDetails, Item : TMDbItem>(
    private val bookmarkRepository: BookmarkDetailsRepository<Item>,
    repository: BaseDetailRepository<ItemDetails>,
    tmdbId: Int?,
) : BaseViewModel<DetailWrapper, Int, DetailUiEvent>(
    repository,
    tmdbId,
    createWarningEvent = DetailUiEvent::ShowWarning,
) {
    private val gson = Gson()

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean>
        get() = _isBookmarked

    fun addBookmark(item: Item) = viewModelScope.launch {
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

    fun onTMDbItemClick(item: TMDbItem) {
        val route = when (item) {
            is Movie -> MovieDetail(item.id)
            is TVShow -> TvShowDetail(item.id)
            else -> throw RuntimeException("Invalid TMDb item type")
        }
        emitEvent(DetailUiEvent.Navigate(route))
    }

    abstract fun onAllSimilarClick(id: Int)

    fun onPersonClick(person: Credit) {
        emitEvent(DetailUiEvent.Navigate(Person(person.id)))
    }

    fun onImageSelected(images: List<TMDbImage>, index: Int) {
        val json = gson.toJson(images, object : TypeToken<List<TMDbImage>>() {}.type)
        emitEvent(DetailUiEvent.Navigate(Images(json, index)))
    }

    fun onSeeAllCastClicked(cast: List<Credit>) {
        val json = gson.toJson(cast, object : TypeToken<List<Cast>>() {}.type)
        emitEvent(DetailUiEvent.Navigate(Cast(json)))
    }

    fun onSeeAllCrewClicked(crew: List<Credit>) {
        val json = gson.toJson(crew, object : TypeToken<List<Crew>>() {}.type)
        emitEvent(DetailUiEvent.Navigate(Crew(json)))
    }

    fun onNavigateUp() {
        emitEvent(DetailUiEvent.NavigateUp)
    }
}
