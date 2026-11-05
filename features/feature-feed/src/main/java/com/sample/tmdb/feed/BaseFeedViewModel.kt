package com.sample.tmdb.feed

import com.sample.tmdb.common.base.BaseCacheViewModel
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.asNavSortType
import com.sample.tmdb.navigation.model.toDetailNavKey

abstract class BaseFeedViewModel<T : TMDbItem>(
    repository: BaseFeedRepository<T>,
    languageRepository: LanguageRepository,
) : BaseCacheViewModel<List<FeedWrapper>, Unit, Unit>(
    repository = repository,
    languageRepository = languageRepository,
) {
    abstract fun onSearchClick()

    fun onTMDbItemClick(item: TMDbItem) {
        emitEvent(UiEvent.Navigate(item.toDetailNavKey()))
    }

    fun onMoreClick(event: FeedNavigationEvent) {
        when (event) {
            is FeedNavigationEvent.More -> {
                when (event.mediaType) {
                    MediaType.MOVIE -> emitEvent(
                        UiEvent.Navigate(TMDbNavKey.PagingMovies(event.sortType.asNavSortType())),
                    )

                    MediaType.TV_SHOW -> emitEvent(
                        UiEvent.Navigate(TMDbNavKey.PagingTvShows(event.sortType.asNavSortType())),
                    )
                }
            }
        }
    }
}
