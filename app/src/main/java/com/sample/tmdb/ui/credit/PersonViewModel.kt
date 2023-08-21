package com.sample.tmdb.ui.credit

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.repository.PersonRepository
import com.sample.tmdb.ui.BaseViewModel
import com.sample.tmdb.ui.MainDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    repository: PersonRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<Person>(
    repository,
    savedStateHandle[MainDestinations.TMDB_PERSON_KEY]
)