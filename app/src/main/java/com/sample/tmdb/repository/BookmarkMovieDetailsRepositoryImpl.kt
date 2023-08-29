package com.sample.tmdb.repository

import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.asDatabaseModel
import com.sample.tmdb.domain.repository.BookmarkItemDetailsRepository
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