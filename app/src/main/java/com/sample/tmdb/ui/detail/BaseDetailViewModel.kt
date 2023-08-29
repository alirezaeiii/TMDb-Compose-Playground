package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.ui.BaseViewModel
import com.sample.tmdb.ui.MainDestinations

open class BaseDetailViewModel<T : TMDbItemDetails>(
    repository: BaseDetailRepository<T>,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailWrapper<T>>(
    repository,
    savedStateHandle[MainDestinations.TMDB_ID_KEY]
)