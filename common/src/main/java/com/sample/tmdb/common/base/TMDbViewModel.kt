package com.sample.tmdb.common.base

open class TMDbViewModel<T>(
    repository: BaseRepository<T>,
    id: Any? = null,
) : BaseViewModel<T>(repository, id) {
    init {
        refresh()
    }
}
