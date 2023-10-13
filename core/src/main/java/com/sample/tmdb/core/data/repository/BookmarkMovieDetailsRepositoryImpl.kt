package com.sample.tmdb.core.data.repository

import com.sample.tmdb.core.data.source.local.MovieDao
import com.sample.tmdb.core.domain.model.Movie
import com.sample.tmdb.core.domain.model.asDatabaseModel
import com.sample.tmdb.core.domain.repository.BookmarkItemDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkMovieDetailsRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : BookmarkItemDetailsRepository<Movie> {

    override suspend fun addBookmark(item: Movie) {
        movieDao.addBookmark(item.asDatabaseModel())
    }

    override suspend fun deleteBookmark(id: Int) {
        movieDao.deleteBookmark(id)
    }

    override suspend fun isBookmarked(id: Int): Boolean = movieDao.isBookmarked(id)
}