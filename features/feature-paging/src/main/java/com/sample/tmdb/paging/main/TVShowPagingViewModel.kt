package com.sample.tmdb.paging.main

import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.PagingRepository
import com.sample.tmdb.domain.utils.TMDb
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = TVShowPagingViewModel.Factory::class)
open class TVShowPagingViewModel @AssistedInject constructor(
    @TMDb repository: PagingRepository<TVShow>,
    @Assisted type: SortType,
    @Assisted genres: String = "",
) : BaseMainPagingViewModel<TVShow>(repository, type = type, genres = genres) {
    override fun onSearchClick() {
        emitEvent(UiEvent.Navigate(TMDbNavKey.SearchTvShows))
    }

    @AssistedFactory
    interface Factory {
        fun create(type: SortType, genres: String = ""): TVShowPagingViewModel
    }
}
