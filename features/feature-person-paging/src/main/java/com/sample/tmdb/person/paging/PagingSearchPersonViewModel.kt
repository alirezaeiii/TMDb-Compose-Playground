package com.sample.tmdb.person.paging

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.domain.repository.PersonPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class PagingSearchPersonViewModel @Inject constructor(
    repository: PersonPagingRepository,
    private val savedStateHandle: SavedStateHandle,
) : BasePersonPagingViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val pagingDataFlow: Flow<PagingData<TrendingPerson>> =
        savedStateHandle
            .getLiveData<String>(KEY_QUERY)
            .asFlow()
            .flatMapLatest {
                repository.fetchResultStream(it)
            }.cachedIn(viewModelScope)

    fun showResult(query: String) {
        if (!shouldShowResult(query)) return
        savedStateHandle[KEY_QUERY] = query
    }

    private fun shouldShowResult(query: String): Boolean = savedStateHandle.get<String>(KEY_QUERY) != query

    companion object {
        private const val KEY_QUERY = "query"
    }
}
