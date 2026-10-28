package com.sample.tmdb.detail

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.model.TrendingPerson
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.asNavPerson
import com.sample.tmdb.navigation.model.toDetailNavKey
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
        emitEvent(DetailUiEvent.Navigate(item.toDetailNavKey()))
    }

    abstract fun onAllSimilarClick(id: Int)

    fun onPersonClick(person: TrendingPerson) {
        emitEvent(DetailUiEvent.Navigate(TMDbNavKey.Person(person.asNavPerson())))
    }

    fun onImageSelected(images: List<TMDbImage>, index: Int) {
        val json = gson.toJson(images, object : TypeToken<List<TMDbImage>>() {}.type)
        emitEvent(DetailUiEvent.Navigate(TMDbNavKey.Images(json, index)))
    }

    fun onSeeAllCastClicked(cast: List<TrendingPerson>) {
        val json = gson.toJson(
            cast.asNavPerson(),
            object : TypeToken<List<com.sample.tmdb.navigation.model.TrendingPerson>>() {}.type,
        )
        emitEvent(DetailUiEvent.Navigate(TMDbNavKey.Cast(json)))
    }

    fun onSeeAllCrewClicked(crew: List<TrendingPerson>) {
        val json = gson.toJson(
            crew.asNavPerson(),
            object : TypeToken<List<com.sample.tmdb.navigation.model.TrendingPerson>>() {}.type,
        )
        emitEvent(DetailUiEvent.Navigate(TMDbNavKey.Crew(json)))
    }

    fun onNavigateUp() {
        emitEvent(DetailUiEvent.NavigateUp)
    }
}
