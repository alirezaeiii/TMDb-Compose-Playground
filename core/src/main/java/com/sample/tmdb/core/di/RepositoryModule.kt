package com.sample.tmdb.core.di

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.core.domain.model.*
import com.sample.tmdb.core.domain.repository.*
import com.sample.tmdb.core.data.repository.*
import com.sample.tmdb.core.di.annotations.Discover
import com.sample.tmdb.core.di.annotations.Latest
import com.sample.tmdb.core.di.annotations.NowPlaying
import com.sample.tmdb.core.di.annotations.Popular
import com.sample.tmdb.core.di.annotations.Search
import com.sample.tmdb.core.di.annotations.TopRated
import com.sample.tmdb.core.di.annotations.Trending
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
    @Trending
    @Binds
    internal abstract fun bindTrendingMoviesRepository(trendingMoviesPagingRepository: TrendingMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Popular
    @Binds
    internal abstract fun bindPopularMoviesRepository(popularMoviesPagingRepository: PopularMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @NowPlaying
    @Binds
    internal abstract fun bindNowPlayingMoviesRepository(nowPlayingMoviesPagingRepository: NowPlayingMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Latest
    @Binds
    internal abstract fun bindUpcomingMoviesRepository(upcomingMoviesPagingRepository: UpcomingMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @TopRated
    @Binds
    internal abstract fun bindTopRatedMoviesRepository(topRatedMoviesPagingRepository: TopRatedMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Discover
    @Binds
    internal abstract fun bindDiscoverMoviesRepository(discoverMoviesPagingRepository: DiscoverMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Search
    @Binds
    internal abstract fun bindSearchMoviesRepository(searchMoviesPagingRepository: SearchMoviesPagingRepository): BasePagingRepository<Movie>

    @Singleton
    @Trending
    @Binds
    internal abstract fun bindTrendingTVShowRepository(trendingTvSeriesPagingRepository: TrendingTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Popular
    @Binds
    internal abstract fun bindPopularTVShowRepository(popularTvSeriesPagingRepository: PopularTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @NowPlaying
    @Binds
    internal abstract fun bindAiringTodayTVShowRepository(airingTodayTvSeriesPagingRepository: AiringTodayTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Latest
    @Binds
    internal abstract fun bindOnTheAirTVShowRepository(onTheAirTvSeriesPagingRepository: OnTheAirTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @TopRated
    @Binds
    internal abstract fun bindTopRatedTVShowRepository(topRatedTvSeriesPagingRepository: TopRatedTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Discover
    @Binds
    internal abstract fun bindDiscoverTVShowRepository(discoverTvSeriesPagingRepository: DiscoverTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Search
    @Binds
    internal abstract fun bindSearchTVShowRepository(searchTvSeriesPagingRepository: SearchTvSeriesPagingRepository): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindPersonRepository(personRepository: PersonRepository): BaseRepository<Person>

    @Singleton
    @Binds
    internal abstract fun bindBookmarkMovieDetailsRepository(bookmarkMovieDetailsRepository: BookmarkMovieDetailsRepositoryImpl): BookmarkItemDetailsRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindBookmarkTVShowDetailsRepository(bookmarkTVShowDetailsRepository: BookmarkTVShowDetailsRepositoryImpl): BookmarkItemDetailsRepository<TVShow>

    @Singleton
    @Binds
    @JvmSuppressWildcards
    internal abstract fun bindBookmarkMovieRepository(bookmarkMovieRepository: BookmarkMovieRepository): BaseRepository<List<Movie>>

    @Singleton
    @Binds
    @JvmSuppressWildcards
    internal abstract fun bindBookmarkTVShowRepository(bookmarkTVShowRepository: BookmarkTVShowRepository): BaseRepository<List<TVShow>>

}