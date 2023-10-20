package com.sample.tmdb.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.core.di.annotations.Search
import com.sample.tmdb.core.domain.model.Movie
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    @Search repository: BasePagingRepository<Movie>,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<Movie>(repository, savedStateHandle)