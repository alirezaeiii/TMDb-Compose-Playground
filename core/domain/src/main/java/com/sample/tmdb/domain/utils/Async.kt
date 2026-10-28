package com.sample.tmdb.domain.utils

sealed class Async<out R> {
    data class Loading(val isRefreshing: Boolean = false) : Async<Nothing>()

    data class Success<out T>(val data: T) : Async<T>()

    data class Error(val message: String, val isWarning: Boolean = false) : Async<Nothing>()
}
