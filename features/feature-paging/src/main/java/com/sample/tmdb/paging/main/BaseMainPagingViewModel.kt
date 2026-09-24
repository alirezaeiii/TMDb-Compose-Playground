package com.sample.tmdb.paging.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.SearchMovies
import com.sample.tmdb.common.ui.SearchTvShows
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.BasePagingViewModel
import com.sample.tmdb.paging.PagingUiEvent
import kotlinx.coroutines.flow.Flow

open class BaseMainPagingViewModel<T : TMDbItem>(repository: BasePagingRepository<T>, id: Int? = null) :
    BasePagingViewModel<T>() {
    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream(id = id).cachedIn(viewModelScope)
}

open class BaseMoviePagingViewModel(repository: BasePagingRepository<Movie>, id: Int? = null) :
    BaseMainPagingViewModel<Movie>(repository, id) {
    override fun onSearchClick() {
        emitEvent(PagingUiEvent.Navigate(SearchMovies))
    }
}

open class BaseTvShowPagingViewModel(repository: BasePagingRepository<TVShow>, id: Int? = null) :
    BaseMainPagingViewModel<TVShow>(repository, id) {
    override fun onSearchClick() {
        emitEvent(PagingUiEvent.Navigate(SearchTvShows))
    }
}
