package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.navigation.MediaType
import com.sample.tmdb.navigation.TMDbNavKey

abstract class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
    languageRepository: LanguageRepository,
) : BaseViewModel<List<FeedWrapper>, Nothing, FeedUiEvent>(
    repository,
    createWarningEvent = FeedUiEvent::ShowWarning,
    languageRepository = languageRepository,
) {
    abstract fun onSearchClick()

    fun onTMDbItemClick(item: TMDbItem) {
        emitEvent(FeedUiEvent.Navigate(item.detailNavKey))
    }

    fun onMoreClick(event: FeedNavigationEvent) {
        when (event) {
            is FeedNavigationEvent.More -> {
                when (event.mediaType) {
                    MediaType.MOVIE -> emitEvent(FeedUiEvent.Navigate(TMDbNavKey.PagingMovies(event.sortType)))
                    MediaType.TV_SHOW -> emitEvent(FeedUiEvent.Navigate(TMDbNavKey.PagingTvShows(event.sortType)))
                }
            }
        }
    }
}
