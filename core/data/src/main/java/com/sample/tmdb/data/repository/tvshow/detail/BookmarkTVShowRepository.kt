package com.sample.tmdb.data.repository.tvshow.detail

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class BookmarkTVShowRepository @Inject constructor(
    private val tvShowDao: TVShowDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<List<@JvmSuppressWildcards TVShow>>(context, ioDispatcher) {
    override suspend fun getSuccessResult(id: Any?): List<TVShow> = tvShowDao.getBookmarks().asDomainModel()
}
