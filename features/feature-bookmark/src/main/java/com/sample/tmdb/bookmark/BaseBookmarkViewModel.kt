package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseBookmarkRepository

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
            is Movie -> MovieDetail(item.id)
            is TVShow -> TvShowDetail(item.id)
            else -> throw RuntimeException("Invalid TMDb item type")
        }
        emitEvent(BookmarkUiEvent.Navigate(route))
    }
}
