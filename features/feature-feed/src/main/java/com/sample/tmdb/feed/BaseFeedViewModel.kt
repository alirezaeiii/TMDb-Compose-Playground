package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseFeedRepository
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
        val route = when (item) {
            is Movie -> TMDbNavKey.MovieDetail(item.id)
            is TVShow -> TMDbNavKey.TvShowDetail(item.id)
            else -> throw RuntimeException("Invalid TMDb item type")
        }
        emitEvent(FeedUiEvent.Navigate(route))
    }

    fun onMoreClick(event: FeedNavigationEvent) {
        when (event) {
            is FeedNavigationEvent.More -> {
                when (event.contentType) {
                    ContentType.MOVIE -> emitEvent(FeedUiEvent.Navigate(TMDbNavKey.PagingMovies(event.sortType)))
                    ContentType.TV_SHOW -> emitEvent(FeedUiEvent.Navigate(TMDbNavKey.PagingTvShows(event.sortType)))
                }
            }
        }
    }
}
