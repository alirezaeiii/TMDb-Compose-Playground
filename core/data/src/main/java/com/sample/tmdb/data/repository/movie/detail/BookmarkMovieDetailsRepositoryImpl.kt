package com.sample.tmdb.data.repository.movie.detail

import com.sample.tmdb.data.source.entity.asDatabaseModel
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkMovieDetailsRepositoryImpl @Inject constructor(private val movieDao: MovieDao) :
    BookmarkDetailsRepository<Movie> {
    override suspend fun addBookmark(item: Movie) {
        movieDao.addBookmark(item.asDatabaseModel())
    }

    override suspend fun deleteBookmark(id: Int) {
        movieDao.deleteBookmark(id)
    }

    override suspend fun isBookmarked(id: Int): Boolean = movieDao.isBookmarked(id)
}
