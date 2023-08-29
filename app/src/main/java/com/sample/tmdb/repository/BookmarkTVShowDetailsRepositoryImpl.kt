package com.sample.tmdb.repository

import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.asDatabaseModel
import com.sample.tmdb.domain.repository.BookmarkTVShowDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkTVShowDetailsRepositoryImpl @Inject constructor(
    private val tvShowDao: TVShowDao
) : BookmarkTVShowDetailsRepository {

    override suspend fun addBookmark(tvShow: TVShow) {
        tvShowDao.addBookmark(tvShow.asDatabaseModel())
    }

    override suspend fun deleteBookmark(id: Int) {
        tvShowDao.deleteBookmark(id)
    }

    override suspend fun isBookmarked(id: Int): Boolean = tvShowDao.isBookmarked(id)
}