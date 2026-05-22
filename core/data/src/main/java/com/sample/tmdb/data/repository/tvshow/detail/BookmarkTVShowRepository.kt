package com.sample.tmdb.data.repository.tvshow.detail

import android.content.Context
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.data.source.entity.asDatabaseModel
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

@Singleton
class BookmarkTVShowRepository @Inject constructor(
    private val tvShowDao: TVShowDao,
    private val tvShowApi: TVShowService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseBookmarkRepository<TVShow>(context, ioDispatcher) {

    override suspend fun getSuccessResult(isRefreshing: Boolean, id: Nothing?): List<TVShow> {
        if (isRefreshing) {
            coroutineScope {
                val jobs = tvShowDao.getBookmarks().asDomainModel().map { tvShow ->
                    async {
                        tvShowDao.addBookmark(
                            tvShowApi.fetchTVSeriesDetail(tvShow.id)
                                .asTVShowDomainModel()
                                .asDatabaseModel(),
                        )
                    }
                }
                jobs.awaitAll()
            }
        }
        return tvShowDao.getBookmarks().asDomainModel()
    }
}
