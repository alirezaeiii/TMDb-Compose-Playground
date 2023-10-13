package com.sample.tmdb.core.data.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.core.data.source.entity.asDomainModel
import com.sample.tmdb.core.data.source.local.TVShowDao
import com.sample.tmdb.core.di.IoDispatcher
import com.sample.tmdb.core.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkTVShowRepository @Inject constructor(
    private val tvShowDao: TVShowDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<List<@JvmSuppressWildcards TVShow>>(context, ioDispatcher) {

    override suspend fun getSuccessResult(id: Any?): List<TVShow> =
        tvShowDao.getBookmarks().asDomainModel()
}