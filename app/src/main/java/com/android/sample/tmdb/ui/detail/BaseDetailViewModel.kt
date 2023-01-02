package com.android.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.android.sample.tmdb.domain.BaseDetailRepository
import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.domain.model.TMDbItemDetails
import com.android.sample.tmdb.ui.BaseViewModel
import com.android.sample.tmdb.ui.MainDestinations

open class BaseDetailViewModel<T : TMDbItemDetails>(
    repository: BaseDetailRepository<T>,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailWrapper<T>>(
    repository,
    savedStateHandle[MainDestinations.TMDB_ID_KEY]
)