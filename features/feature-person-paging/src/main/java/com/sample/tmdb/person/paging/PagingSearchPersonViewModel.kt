package com.sample.tmdb.person.paging

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.sample.tmdb.common.base.BaseSearchPagingViewModel
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.domain.model.TrendingPerson
import com.sample.tmdb.domain.repository.PagingRepository
import com.sample.tmdb.domain.utils.Search
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.asNavPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PagingSearchPersonViewModel @Inject constructor(
    @Search private val repository: PagingRepository<TrendingPerson>,
    savedStateHandle: SavedStateHandle,
) : BaseSearchPagingViewModel<TrendingPerson>(savedStateHandle) {

    override fun fetchResultStream(query: String): Flow<PagingData<TrendingPerson>> =
        repository.fetchResultStream(query)

    override fun onItemClick(item: TrendingPerson) {
        emitEvent(UiEvent.Navigate(TMDbNavKey.Person(item.asNavPerson())))
    }
}
