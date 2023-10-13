package com.sample.tmdb.common.base

open class BaseRefreshViewModel<T>(
    repository: BaseRepository<T>,
    id: Any? = null
) : BaseViewModel<T>(repository, id) {

    init {
        refresh()
    }
}