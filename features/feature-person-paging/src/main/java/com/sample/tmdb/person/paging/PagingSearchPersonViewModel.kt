package com.sample.tmdb.person.paging

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.sample.tmdb.common.base.BaseSearchPagingViewModel
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.domain.repository.PersonPagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PagingSearchPersonViewModel @Inject constructor(
    private val repository: PersonPagingRepository,
    savedStateHandle: SavedStateHandle,
) : BaseSearchPagingViewModel<TrendingPerson>(savedStateHandle) {

    override fun fetchResultStream(query: String): Flow<PagingData<TrendingPerson>> =
        repository.fetchResultStream(query)

    override fun onItemClick(item: TrendingPerson) {
        emitEvent(PagingUiEvent.Navigate(TMDbNavKey.Person(item.id)))
    }
}
