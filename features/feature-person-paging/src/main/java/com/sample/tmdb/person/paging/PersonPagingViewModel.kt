package com.sample.tmdb.person.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.domain.repository.PersonPagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PersonPagingViewModel @Inject constructor(repository: PersonPagingRepository) : BasePersonPagingViewModel() {

    override val pagingDataFlow: Flow<PagingData<TrendingPerson>> =
        repository.fetchResultStream().cachedIn(viewModelScope)

    fun onSearchClick() {
        emitEvent(PagingPersonUiEvent.Navigate(TMDbNavKey.SearchPerson))
    }
}
