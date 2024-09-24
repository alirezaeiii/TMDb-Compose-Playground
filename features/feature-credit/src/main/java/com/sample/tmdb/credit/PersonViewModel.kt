package com.sample.tmdb.credit

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.TMDbViewModel
import com.sample.tmdb.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(repository: BaseRepository<Person>, savedStateHandle: SavedStateHandle) :
    TMDbViewModel<Person>(
        repository,
        savedStateHandle[MainDestinations.TMDB_PERSON_KEY],
    )
