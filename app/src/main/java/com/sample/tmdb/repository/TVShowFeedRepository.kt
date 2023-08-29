package com.sample.tmdb.repository

import android.content.Context
import com.sample.tmdb.R
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.di.IoDispatcher
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowFeedRepository @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val tvShowApi: TVShowService,
) : BaseFeedRepository<TVShow>(context, ioDispatcher) {

    override suspend fun popularItems(): List<TVShow> =
        tvShowApi.popularTVSeries().items.asTVShowDomainModel()

    override suspend fun latestItems(): List<TVShow> =
        tvShowApi.onTheAirTVSeries().items.asTVShowDomainModel()

    override suspend fun topRatedItems(): List<TVShow> =
        tvShowApi.topRatedTVSeries().items.asTVShowDomainModel()

    override suspend fun trendingItems(): List<TVShow> =
        tvShowApi.trendingTVSeries().items.asTVShowDomainModel()

    override suspend fun nowPlayingItems(): List<TVShow> =
        tvShowApi.airingTodayTVSeries().items.asTVShowDomainModel()

    override fun getNowPlayingResId(): Int = R.string.text_airing_today

    override fun getLatestResId(): Int = R.string.text_on_the_air
}