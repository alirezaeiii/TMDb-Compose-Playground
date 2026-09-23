package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkMovieViewModel @Inject constructor(
    repository: BaseBookmarkRepository<Movie>,
    languageRepository: LanguageRepository,
) : BaseViewModel<List<Movie>, Nothing, BookmarkUiEvent>(
    repository,
    createWarningEvent = BookmarkUiEvent::ShowWarning,
    languageRepository = languageRepository,
    loadDataOnInit = false,
) {
    fun onMovieClick(movie: TMDbItem) {
        emitEvent(BookmarkUiEvent.Navigate(MovieDetail(movie.id)))
    }
}
