package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkTVShowViewModel @Inject constructor(
    repository: BaseBookmarkRepository<TVShow>,
    languageRepository: LanguageRepository,
) : BaseViewModel<List<TVShow>, Nothing, BookmarkUiEvent>(
    repository,
    createWarningEvent = BookmarkUiEvent::ShowWarning,
    languageRepository = languageRepository,
    loadDataOnInit = false,
) {
    fun onTVShowClick(tvShow: TMDbItem) {
        emitEvent(BookmarkUiEvent.Navigate(TvShowDetail(tvShow.id)))
    }
}
