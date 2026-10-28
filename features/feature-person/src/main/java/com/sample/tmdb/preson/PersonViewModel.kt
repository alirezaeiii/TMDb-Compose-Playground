package com.sample.tmdb.preson

import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.domain.model.Media
import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = PersonViewModel.Factory::class)
class PersonViewModel @AssistedInject constructor(repository: BaseRepository<Person, Int>, @Assisted personId: Int) :
    BaseViewModel<Person, Int, PersonUiEvent>(
        repository,
        personId,
    ) {

    fun onNavigateUp() {
        emitEvent(PersonUiEvent.NavigateUp)
    }

    fun onMediaClick(media: Media) {
        when (media.mediaType) {
            MediaType.MOVIE -> emitEvent(PersonUiEvent.Navigate(TMDbNavKey.MovieDetail(media.id)))
            MediaType.TV_SHOW -> emitEvent(PersonUiEvent.Navigate(TMDbNavKey.TvShowDetail(media.id)))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(personId: Int): PersonViewModel
    }
}
