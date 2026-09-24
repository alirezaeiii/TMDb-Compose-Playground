package com.sample.tmdb.feed

import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.ui.SearchTvShows
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(
    repository: BaseFeedRepository<TVShow>,
    languageRepository: LanguageRepository,
) : BaseFeedViewModel<TVShow>(repository, languageRepository) {
    override fun onSearchClick() {
        emitEvent(FeedUiEvent.Navigate(SearchTvShows))
    }
}
