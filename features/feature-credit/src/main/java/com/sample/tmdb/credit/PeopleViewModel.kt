package com.sample.tmdb.credit

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.People
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.TrendingPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    repository: BaseRepository<People, Nothing>,
    languageRepository: LanguageRepository,
) : BaseViewModel<People, Nothing, PeopleUIEvent>(
    repository,
    createWarningEvent = PeopleUIEvent::ShowWarning,
    languageRepository = languageRepository,
) {
    fun onPersonClick(people: TrendingPerson) {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.Person(people)))
    }

    fun onMoreClick() {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.PersonPaging))
    }

    fun onSearchClick() {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.SearchPerson))
    }
}
