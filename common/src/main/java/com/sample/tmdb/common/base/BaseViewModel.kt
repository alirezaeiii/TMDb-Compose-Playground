package com.sample.tmdb.common.base

import androidx.lifecycle.viewModelScope
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel<DataType, Param>(
    private val repository: BaseRepository<DataType, Param>,
    private val id: Param? = null,
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

    fun refresh(isUserRefresh: Boolean = false, languageCode: String? = null) =
        executeRefresh(isUserRefresh, languageCode)

    override fun executeRefresh(isUserRefresh: Boolean, languageCode: String?) {
        repository.getResult(isUserRefresh, id)
            .onEach { reduce(it, languageCode) }
            .launchIn(viewModelScope)
    }
}
