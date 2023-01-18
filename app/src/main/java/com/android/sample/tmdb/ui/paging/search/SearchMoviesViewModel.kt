package com.android.sample.tmdb.ui.paging.search

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.SearchMoviesPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    repository: SearchMoviesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<Movie>(repository, savedStateHandle)