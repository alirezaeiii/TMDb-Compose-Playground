package com.sample.tmdb.credit

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.domain.model.People
import com.sample.tmdb.domain.model.TrendingPerson
import com.sample.tmdb.domain.model.asNavPerson
import com.sample.tmdb.navigation.TMDbNavKey
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
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.Person(people.asNavPerson())))
    }

    fun onMoreClick() {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.PersonPaging))
    }

    fun onSearchClick() {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.SearchPerson))
    }
}
