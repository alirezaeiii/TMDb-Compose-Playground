package com.sample.tmdb.credit

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.People
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(repository: BaseRepository<People, Nothing>) :
    BaseViewModel<People, Nothing, PeopleUIEvent>(
        repository,
        createWarningEvent = PeopleUIEvent::ShowWarning,
    ) {
    fun onPersonClick(people: TrendingPerson) {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.Person(people.id)))
    }

    fun onMoreClick() {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.PersonPaging))
    }

    fun onSearchClick() {
        emitEvent(PeopleUIEvent.Navigate(TMDbNavKey.SearchPerson))
    }
}
