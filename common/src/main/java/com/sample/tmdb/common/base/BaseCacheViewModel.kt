package com.sample.tmdb.common.base

import androidx.lifecycle.viewModelScope
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseCacheViewModel<DataType, QueryType, FetchType>(
    private val repository: BaseCacheRepository<DataType, QueryType, FetchType>,
    private val queryParam: QueryType? = null,
    private val fetchParam: FetchType? = null,
    languageRepository: LanguageRepository? = null,
    loadDataOnInit: Boolean = true,
) : BaseCoreViewModel<DataType>(
    languageRepository,
) {

    init {
        if (loadDataOnInit) {
            refresh()
        }
    }

    private var job: Job? = null

    override fun refresh(isUserRefresh: Boolean, languageCode: String?) {
        job?.cancel()

        job = repository.getResult(
            queryParam,
            fetchParam,
            isUserRefresh,
        ).onEach { reduce(it, languageCode) }.launchIn(viewModelScope)
    }
}
