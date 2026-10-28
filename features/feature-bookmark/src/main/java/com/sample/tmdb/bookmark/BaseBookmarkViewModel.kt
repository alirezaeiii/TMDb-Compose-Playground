package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.navigation.model.toDetailNavKey

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
        emitEvent(BookmarkUiEvent.Navigate(item.toDetailNavKey()))
    }
}
