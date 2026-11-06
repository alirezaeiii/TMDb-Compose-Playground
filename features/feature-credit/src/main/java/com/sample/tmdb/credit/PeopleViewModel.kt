package com.sample.tmdb.credit

import com.sample.tmdb.common.base.BaseCacheViewModel
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.People
import com.sample.tmdb.domain.model.TrendingPerson
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.asNavPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    repository: BaseCacheRepository<People, Unit, Unit>,
    languageRepository: LanguageRepository,
) : BaseCacheViewModel<People, Unit, Unit>(
    repository = repository,
    languageRepository = languageRepository,
) {
    fun onPersonClick(people: TrendingPerson) {
        emitEvent(UiEvent.Navigate(TMDbNavKey.Person(people.asNavPerson())))
    }

    fun onMoreClick() {
        emitEvent(UiEvent.Navigate(TMDbNavKey.PersonPaging))
    }

    fun onSearchClick() {
        emitEvent(UiEvent.Navigate(TMDbNavKey.SearchPerson))
    }
}
