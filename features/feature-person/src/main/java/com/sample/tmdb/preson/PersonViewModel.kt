package com.sample.tmdb.preson

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.domain.model.Person
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = PersonViewModel.Factory::class)
class PersonViewModel @AssistedInject constructor(
    repository: BaseRepository<Person, String>,
    @Assisted personId: Int,
) : BaseViewModel<Person, String>(
    repository,
    personId.toString(),
) {

    @AssistedFactory
    interface Factory {
        fun create(personId: Int): PersonViewModel
    }
}
