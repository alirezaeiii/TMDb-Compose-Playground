package com.sample.tmdb.ui.credit

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.ui.BaseRefreshViewModel
import com.sample.tmdb.ui.MainDestinations
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