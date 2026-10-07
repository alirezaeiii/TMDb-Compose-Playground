package com.sample.tmdb.paging.main

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.TMDb
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.PagingUiEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = MoviePagingViewModel.Factory::class)
open class MoviePagingViewModel @AssistedInject constructor(
    @TMDb repository: BasePagingRepository<Movie>,
    @Assisted id: Int? = null,
    @Assisted type: SortType,
) : BaseMainPagingViewModel<Movie>(repository, id, type) {
    override fun onSearchClick() {
        emitEvent(PagingUiEvent.Navigate(TMDbNavKey.SearchMovies))
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int? = null, type: SortType): MoviePagingViewModel
    }
}