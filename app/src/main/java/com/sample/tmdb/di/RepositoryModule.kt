package com.sample.tmdb.di

import com.sample.tmdb.domain.BaseDetailRepository
import com.sample.tmdb.domain.BaseFeedRepository
import com.sample.tmdb.domain.BasePagingRepository
import com.sample.tmdb.domain.BaseRepository
import com.sample.tmdb.domain.model.*
import com.sample.tmdb.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindMovieFeedRepository(movieFeedRepository: MovieFeedRepository): BaseFeedRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindTVShowFeedRepository(tvShowFeedRepository: TVShowFeedRepository): BaseFeedRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindMovieDetailRepository(movieDetailRepository: MovieDetailRepository): BaseDetailRepository<MovieDetails>

    @Singleton
    @Binds
    internal abstract fun bindTVShowDetailRepository(tvShowDetailRepository: TVShowDetailRepository): BaseDetailRepository<TvDetails>

    @Singleton
    @Binds
    internal abstract fun bindTrendingMoviesRepository(trendingMoviesPagingRepository: TrendingMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindPopularMoviesRepository(popularMoviesPagingRepository: PopularMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindNowPlayingMoviesRepository(nowPlayingMoviesPagingRepository: NowPlayingMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindUpcomingMoviesRepository(upcomingMoviesPagingRepository: UpcomingMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindTopRatedMoviesRepository(topRatedMoviesPagingRepository: TopRatedMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindSearchMoviesRepository(searchMoviesPagingRepository: SearchMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindTrendingTVShowRepository(trendingTvSeriesPagingRepository: TrendingTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindPopularTVShowRepository(popularTvSeriesPagingRepository: PopularTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindAiringTodayTVShowRepository(airingTodayTvSeriesPagingRepository: AiringTodayTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindOnTheAirTVShowRepository(onTheAirTvSeriesPagingRepository: OnTheAirTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindTopRatedTVShowRepository(topRatedTvSeriesPagingRepository: TopRatedTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindSearchTVShowRepository(searchTvSeriesPagingRepository: SearchTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindPersonRepository(personRepository: PersonRepository): BaseRepository<Person>
}