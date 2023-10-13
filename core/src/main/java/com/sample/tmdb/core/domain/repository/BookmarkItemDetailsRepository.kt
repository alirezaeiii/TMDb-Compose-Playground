package com.sample.tmdb.core.domain.repository

import com.sample.tmdb.common.model.TMDbItem

interface BookmarkItemDetailsRepository<T: TMDbItem> {

    suspend fun addBookmark(item: T)

    suspend fun deleteBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean
}