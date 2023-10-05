package com.sample.tmdb.ui.paging.search

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.data.repository.SearchMoviesPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    repository: SearchMoviesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<Movie>(repository, savedStateHandle)