package com.android.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.repository.TVShowDetailRepository
import com.android.sample.tmdb.ui.BaseViewModel
import com.android.sample.tmdb.ui.DetailScreens.Companion.TMDb_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    repository: TVShowDetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailWrapper>(
    repository,
    savedStateHandle.get<TVShow>(TMDb_ITEM)?.id,
)