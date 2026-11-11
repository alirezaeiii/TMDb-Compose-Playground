package com.sample.tmdb.paging.main

import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.PagingRepository
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = SimilarMoviePagingViewModel.Factory::class)
open class SimilarMoviePagingViewModel @AssistedInject constructor(
    @Similar repository: PagingRepository<Movie>,
    @Assisted id: Int,
) : BaseMainPagingViewModel<Movie>(repository, id) {
    override fun onSearchClick() {
        emitEvent(UiEvent.Navigate(TMDbNavKey.SearchMovies))
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): SimilarMoviePagingViewModel
    }
}
