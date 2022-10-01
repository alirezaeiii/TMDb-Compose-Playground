package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.R
import com.android.sample.tmdb.data.ItemWrapper
import com.android.sample.tmdb.data.Movie
import com.android.sample.tmdb.di.IoDispatcher
import com.android.sample.tmdb.domain.BaseFeedRepository
import com.android.sample.tmdb.network.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieFeedRepository @Inject constructor(
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val movieApi: MovieService,
): BaseFeedRepository<Movie>(context, ioDispatcher) {

    override suspend fun popularItems(): ItemWrapper<Movie> = movieApi.popularMovies()

    override suspend fun latestItems(): ItemWrapper<Movie> = movieApi.upcomingMovies()

    override suspend fun topRatedItems(): ItemWrapper<Movie> = movieApi.topRatedMovies()

    override suspend fun trendingItems(): ItemWrapper<Movie> = movieApi.trendingMovies()

    override suspend fun nowPlayingItems(): ItemWrapper<Movie> = movieApi.nowPlayingMovies()

    override fun getNowPlayingResId(): Int = R.string.text_now_playing

    override fun getLatestResId(): Int = R.string.text_upcoming
}