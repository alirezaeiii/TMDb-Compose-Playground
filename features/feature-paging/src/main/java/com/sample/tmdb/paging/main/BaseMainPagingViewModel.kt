package com.sample.tmdb.paging.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.TMDb
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.BasePagingViewModel
import com.sample.tmdb.paging.PagingUiEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseMainPagingViewModel<T : TMDbItem>(
    repository: BasePagingRepository<T>,
    id: Int? = null,
    type: SortType,
) : BasePagingViewModel<T>() {
    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream(id = id, type = type).cachedIn(viewModelScope)

    abstract fun onSearchClick()
}

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

@HiltViewModel(assistedFactory = TvShowPagingViewModel.Factory::class)
open class TvShowPagingViewModel @AssistedInject constructor(
    @TMDb repository: BasePagingRepository<TVShow>,
    @Assisted id: Int? = null,
    @Assisted type: SortType,
) : BaseMainPagingViewModel<TVShow>(repository, id, type) {
    override fun onSearchClick() {
        emitEvent(PagingUiEvent.Navigate(TMDbNavKey.SearchTvShows))
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int? = null, type: SortType): TvShowPagingViewModel
    }
}
