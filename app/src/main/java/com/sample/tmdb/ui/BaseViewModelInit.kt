package com.sample.tmdb.ui

import com.sample.tmdb.domain.repository.BaseRepository

open class BaseViewModelInit<T>(
    repository: BaseRepository<T>,
    id: Any? = null
) : BaseViewModel<T>(repository, id) {

    init {
        refresh()
    }
}