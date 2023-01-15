package com.android.sample.tmdb.ui.paging.movie

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.SearchMoviesPagingRepository
import com.android.sample.tmdb.ui.paging.BaseSearchPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    repository: SearchMoviesPagingRepository,
    savedStateHandle: SavedStateHandle
) : BaseSearchPagingViewModel<Movie>(repository, savedStateHandle)