package com.sample.tmdb.domain.repository

import com.sample.tmdb.domain.model.Movie

interface BookmarkMovieDetailsRepository {

    suspend fun addBookmark(movie: Movie)

    suspend fun deleteBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean
}