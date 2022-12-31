package com.android.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: MovieDetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel(repository, savedStateHandle)