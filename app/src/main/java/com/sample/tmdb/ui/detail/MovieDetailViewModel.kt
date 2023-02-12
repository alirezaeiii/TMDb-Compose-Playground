package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: MovieDetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<MovieDetails>(repository, savedStateHandle)