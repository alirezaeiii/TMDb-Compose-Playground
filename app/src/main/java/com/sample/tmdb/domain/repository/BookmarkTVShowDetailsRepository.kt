package com.sample.tmdb.domain.repository

import com.sample.tmdb.domain.model.TVShow

interface BookmarkTVShowDetailsRepository {

    suspend fun addBookmark(tvShow: TVShow)

    suspend fun deleteBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean
}