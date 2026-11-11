package com.sample.tmdb.paging.main

import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.PagingRepository
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = SimilarTvShowPagingViewModel.Factory::class)
open class SimilarTvShowPagingViewModel @AssistedInject constructor(
    @Similar repository: PagingRepository<TVShow>,
    @Assisted id: Int,
) : BaseMainPagingViewModel<TVShow>(repository, id) {
    override fun onSearchClick() {
        emitEvent(UiEvent.Navigate(TMDbNavKey.SearchTvShows))
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): SimilarTvShowPagingViewModel
    }
}
