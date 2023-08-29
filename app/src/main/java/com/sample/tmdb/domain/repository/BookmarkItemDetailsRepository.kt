package com.sample.tmdb.domain.repository

import com.sample.tmdb.domain.model.TMDbItem

interface BookmarkItemDetailsRepository<T: TMDbItem> {

    suspend fun addBookmark(item: T)

    suspend fun deleteBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean
}