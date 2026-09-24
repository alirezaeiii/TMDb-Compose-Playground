package com.sample.tmdb.feed

import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.ui.SearchMovies
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(
    repository: BaseFeedRepository<Movie>,
    languageRepository: LanguageRepository,
) : BaseFeedViewModel<Movie>(repository, languageRepository) {
    override fun onSearchClick() {
        emitEvent(FeedUiEvent.Navigate(SearchMovies))
    }
}
