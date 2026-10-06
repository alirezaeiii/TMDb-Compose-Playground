package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import com.sample.tmdb.navigation.TMDbNavKey

open class BaseBookmarkViewModel<T : TMDbItem>(
    repository: BaseBookmarkRepository<T>,
    languageRepository: LanguageRepository,
) : BaseViewModel<List<T>, Nothing, BookmarkUiEvent>(
    repository,
    createWarningEvent = BookmarkUiEvent::ShowWarning,
    languageRepository = languageRepository,
    loadDataOnInit = false,
) {
    fun onTMDbItemClick(item: TMDbItem) {
        val route = when (item) {
            is Movie -> TMDbNavKey.MovieDetail(item.id)
            is TVShow -> TMDbNavKey.TvShowDetail(item.id)
            else -> throw RuntimeException("Invalid TMDb item type")
        }
        emitEvent(BookmarkUiEvent.Navigate(route))
    }
}
