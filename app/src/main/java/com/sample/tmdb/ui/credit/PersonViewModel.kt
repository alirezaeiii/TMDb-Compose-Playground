package com.sample.tmdb.ui.credit

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.ui.BaseViewModelInit
import com.sample.tmdb.ui.MainDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    repository: BaseRepository<Person>,
    savedStateHandle: SavedStateHandle
) : BaseViewModelInit<Person>(
    repository,
    savedStateHandle[MainDestinations.TMDB_PERSON_KEY]
)