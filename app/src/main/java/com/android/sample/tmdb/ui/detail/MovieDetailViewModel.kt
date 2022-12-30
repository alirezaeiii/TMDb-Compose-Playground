package com.android.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.repository.MovieDetailRepository
import com.android.sample.tmdb.ui.BaseViewModel
import com.android.sample.tmdb.ui.DetailScreens.Companion.TMDb_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: MovieDetailRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailWrapper>(
    repository,
    savedStateHandle.get<Movie>(TMDb_ITEM)?.id
)