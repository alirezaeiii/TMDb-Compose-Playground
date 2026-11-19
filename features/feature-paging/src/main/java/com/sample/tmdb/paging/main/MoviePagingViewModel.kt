package com.sample.tmdb.paging.main

import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.repository.PagingRepository
import com.sample.tmdb.domain.utils.TMDb
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = MoviePagingViewModel.Factory::class)
open class MoviePagingViewModel @AssistedInject constructor(
    @TMDb repository: PagingRepository<Movie>,
    @Assisted type: SortType,
    @Assisted genres: String = "",
) : BaseMainPagingViewModel<Movie>(repository, type = type, genres = genres) {
    override fun onSearchClick() {
        emitEvent(UiEvent.Navigate(TMDbNavKey.SearchMovies))
    }

    @AssistedFactory
    interface Factory {
        fun create(type: SortType, genres: String = ""): MoviePagingViewModel
    }
}
