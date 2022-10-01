package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.R
import com.android.sample.tmdb.data.ItemWrapper
import com.android.sample.tmdb.data.TVShow
import com.android.sample.tmdb.di.IoDispatcher
import com.android.sample.tmdb.domain.BaseFeedRepository
import com.android.sample.tmdb.network.TVShowService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowFeedRepository @Inject constructor(
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val tvShowApi: TVShowService,
) : BaseFeedRepository<TVShow>(context, ioDispatcher) {

    override suspend fun popularItems(): ItemWrapper<TVShow> = tvShowApi.popularTVSeries()

    override suspend fun latestItems(): ItemWrapper<TVShow> = tvShowApi.onTheAirTVSeries()

    override suspend fun topRatedItems(): ItemWrapper<TVShow> = tvShowApi.topRatedTVSeries()

    override suspend fun trendingItems(): ItemWrapper<TVShow> = tvShowApi.trendingTVSeries()

    override suspend fun nowPlayingItems(): ItemWrapper<TVShow> = tvShowApi.airingTodayTVSeries()

    override fun getNowPlayingResId(): Int = R.string.text_airing_today

    override fun getLatestResId(): Int = R.string.text_on_the_air
}