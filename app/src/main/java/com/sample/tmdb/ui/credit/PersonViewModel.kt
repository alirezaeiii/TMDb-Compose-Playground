package com.sample.tmdb.ui.credit

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.base.BaseRefreshViewModel
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.MainDestinations
import com.sample.tmdb.core.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    repository: BaseRepository<Person>,
    savedStateHandle: SavedStateHandle
) : BaseRefreshViewModel<Person>(
    repository,
    savedStateHandle[MainDestinations.TMDB_PERSON_KEY]
)