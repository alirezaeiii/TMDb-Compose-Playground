package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.ui.SearchMovies
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.feed.utils.toNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(
    repository: BaseFeedRepository<Movie>,
    languageRepository: LanguageRepository,
) : BaseViewModel<List<FeedWrapper>, Nothing, FeedUiEvent>(
    repository,
    createWarningEvent = FeedUiEvent::ShowWarning,
    languageRepository = languageRepository,
) {
    fun onMovieClick(movie: TMDbItem) {
        emitEvent(FeedUiEvent.Navigate(MovieDetail(movie.id)))
    }

    fun onMoreClick(event: FeedNavigationEvent) {
        when (event) {
            is FeedNavigationEvent.More -> {
                emitEvent(FeedUiEvent.Navigate(event.toNavKey()))
            }
        }
    }

    fun onSearchClick() {
        emitEvent(FeedUiEvent.Navigate(SearchMovies))
    }
}
