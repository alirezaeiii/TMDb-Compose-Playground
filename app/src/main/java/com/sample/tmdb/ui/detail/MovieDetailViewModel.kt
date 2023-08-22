package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.BaseDetailRepository
import com.sample.tmdb.domain.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: BaseDetailRepository<MovieDetails>,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<MovieDetails>(repository, savedStateHandle)