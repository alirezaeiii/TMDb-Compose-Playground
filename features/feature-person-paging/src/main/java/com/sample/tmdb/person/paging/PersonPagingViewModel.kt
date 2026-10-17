package com.sample.tmdb.person.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PersonPagingViewModel @Inject constructor(repository: BasePagingRepository<TrendingPerson>) :
    BasePagingViewModel<TrendingPerson>() {

    override val pagingDataFlow: Flow<PagingData<TrendingPerson>> =
        repository.fetchResultStream().cachedIn(viewModelScope)

    override fun onItemClick(item: TrendingPerson) {
        emitEvent(PagingUiEvent.Navigate(TMDbNavKey.Person(item.id)))
    }

    fun onSearchClick() {
        emitEvent(PagingUiEvent.Navigate(TMDbNavKey.SearchPerson))
    }
}
