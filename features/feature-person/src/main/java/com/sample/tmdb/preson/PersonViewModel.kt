package com.sample.tmdb.preson

import com.sample.tmdb.common.base.BaseCacheViewModel
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.Media
import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = PersonViewModel.Factory::class)
class PersonViewModel @AssistedInject constructor(
    repository: BaseCacheRepository<Person, Int, Int>,
    @Assisted personId: Int,
) : BaseCacheViewModel<Person, Int, Int>(
    repository = repository,
    queryParam = personId,
    fetchParam = personId,
) {

    fun onNavigateUp() {
        emitEvent(UiEvent.NavigateUp)
    }

    fun onMediaClick(media: Media) {
        when (media.mediaType) {
            MediaType.MOVIE -> emitEvent(UiEvent.Navigate(TMDbNavKey.MovieDetail(media.id)))
            MediaType.TV_SHOW -> emitEvent(UiEvent.Navigate(TMDbNavKey.TvShowDetail(media.id)))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(personId: Int): PersonViewModel
    }
}
