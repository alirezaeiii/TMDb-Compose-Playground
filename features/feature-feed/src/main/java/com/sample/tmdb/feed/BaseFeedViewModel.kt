package com.sample.tmdb.feed

import androidx.lifecycle.viewModelScope
import com.sample.tmdb.common.base.BaseCacheViewModel
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.Feed
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.asNavSortType
import com.sample.tmdb.navigation.utils.toDetailNavKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFeedViewModel<T : TMDbItem>(
    private val repository: BaseFeedRepository<T>,
    languageRepository: LanguageRepository,
) : BaseCacheViewModel<Feed, Unit, Unit>(
    repository = repository,
    languageRepository = languageRepository,
) {
    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres.asStateFlow()

    private val _selectedGenreIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedGenreIds: StateFlow<Set<Int>> = _selectedGenreIds.asStateFlow()

    init {
        viewModelScope.launch {
            state.collectLatest { viewState ->
                viewState.items?.genres?.let {
                    _genres.value = it
                }
            }
        }
    }

    fun onGenreSelected(genre: Genre) {
        val current = _selectedGenreIds.value.toMutableSet()
        if (current.contains(genre.id)) {
            current.remove(genre.id)
        } else {
            current.add(genre.id)
        }
        _selectedGenreIds.value = current
        repository.setSelectedGenres(current.toTypedArray())
        refresh(false)
    }

    abstract fun onSearchClick()

    fun onTMDbItemClick(item: TMDbItem) {
        emitEvent(UiEvent.Navigate(item.toDetailNavKey()))
    }

    fun onMoreClick(event: FeedNavigationEvent) {
        when (event) {
            is FeedNavigationEvent.More -> {
                val genresKey = selectedGenreIds.value.sorted().joinToString(",")
                when (event.mediaType) {
                    MediaType.MOVIE -> emitEvent(
                        UiEvent.Navigate(TMDbNavKey.PagingMovies(event.sortType.asNavSortType(), genresKey)),
                    )

                    MediaType.TV_SHOW -> emitEvent(
                        UiEvent.Navigate(TMDbNavKey.PagingTvShows(event.sortType.asNavSortType(), genresKey)),
                    )
                }
            }
        }
    }
}
