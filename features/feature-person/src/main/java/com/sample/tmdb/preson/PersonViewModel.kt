package com.sample.tmdb.preson

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    repository: BaseRepository<Person, String>,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<Person, String>(
    repository,
    id = savedStateHandle[MainDestinations.TMDB_PERSON_KEY],
)
