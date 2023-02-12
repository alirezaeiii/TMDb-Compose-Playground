package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.TvDetails
import com.sample.tmdb.repository.TVShowDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    repository: TVShowDetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<TvDetails>(repository, savedStateHandle)