package com.android.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.repository.TVShowDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    repository: TVShowDetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel(repository, savedStateHandle)