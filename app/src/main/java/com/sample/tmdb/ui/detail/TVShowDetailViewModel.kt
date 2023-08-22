package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.BaseDetailRepository
import com.sample.tmdb.domain.model.TvDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    repository: BaseDetailRepository<TvDetails>,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<TvDetails>(repository, savedStateHandle)