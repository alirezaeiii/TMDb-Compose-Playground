package com.sample.tmdb.common.base

import androidx.lifecycle.viewModelScope
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel<T, S>(
    private val repository: BaseRepository<T, S>,
    private val id: S? = null,
    languageRepository: LanguageRepository? = null,
    loadDataOnInit: Boolean = true,
) : BaseCoreViewModel<T>(
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
