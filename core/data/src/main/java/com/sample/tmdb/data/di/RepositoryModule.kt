package com.sample.tmdb.data.di

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.data.repository.PersonRepository
import com.sample.tmdb.data.repository.movie.MovieFeedRepository
import com.sample.tmdb.data.repository.movie.detail.BookmarkMovieDetailsRepositoryImpl
import com.sample.tmdb.data.repository.movie.detail.BookmarkMovieRepository
import com.sample.tmdb.data.repository.movie.detail.MovieDetailRepository
import com.sample.tmdb.data.repository.movie.paging.DiscoverMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.NowPlayingMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.PopularMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.SearchMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.SimilarMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.TopRatedMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.TrendingMoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.UpcomingMoviesPagingRepository
import com.sample.tmdb.data.repository.tvshow.TVShowFeedRepository
import com.sample.tmdb.data.repository.tvshow.detail.BookmarkTVShowDetailsRepositoryImpl
import com.sample.tmdb.data.repository.tvshow.detail.BookmarkTVShowRepository
import com.sample.tmdb.data.repository.tvshow.detail.TVShowDetailRepository
import com.sample.tmdb.data.repository.tvshow.paging.AiringTodayTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.DiscoverTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.OnTheAirTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.PopularTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.SearchTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.SimilarTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.TopRatedTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.TrendingTVSeriesPagingRepository
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TvDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import com.sample.tmdb.domain.utils.Discover
import com.sample.tmdb.domain.utils.Latest
import com.sample.tmdb.domain.utils.NowPlaying
import com.sample.tmdb.domain.utils.Popular
import com.sample.tmdb.domain.utils.Search
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.domain.utils.TopRated
import com.sample.tmdb.domain.utils.Trending
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
    internal abstract fun bindTVShowFeedRepository(
        tvShowFeedRepository: TVShowFeedRepository,
    ): BaseFeedRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindMovieDetailRepository(
        movieDetailRepository: MovieDetailRepository,
    ): BaseDetailRepository<MovieDetails>

    @Singleton
    @Binds
    internal abstract fun bindTVShowDetailRepository(
        tvShowDetailRepository: TVShowDetailRepository,
    ): BaseDetailRepository<TvDetails>

    @Singleton
    @Trending
    @Binds
    internal abstract fun bindTrendingMoviesRepository(
        trendingMoviesPagingRepository: TrendingMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Popular
    @Binds
    internal abstract fun bindPopularMoviesRepository(
        popularMoviesPagingRepository: PopularMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @NowPlaying
    @Binds
    internal abstract fun bindNowPlayingMoviesRepository(
        nowPlayingMoviesPagingRepository: NowPlayingMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Latest
    @Binds
    internal abstract fun bindUpcomingMoviesRepository(
        upcomingMoviesPagingRepository: UpcomingMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @TopRated
    @Binds
    internal abstract fun bindTopRatedMoviesRepository(
        topRatedMoviesPagingRepository: TopRatedMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Discover
    @Binds
    internal abstract fun bindDiscoverMoviesRepository(
        discoverMoviesPagingRepository: DiscoverMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Similar
    @Binds
    internal abstract fun bindSimilarMoviesRepository(
        similarMoviesPagingRepository: SimilarMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Search
    @Binds
    internal abstract fun bindSearchMoviesRepository(
        searchMoviesPagingRepository: SearchMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Trending
    @Binds
    internal abstract fun bindTrendingTVShowRepository(
        trendingTvSeriesPagingRepository: TrendingTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Popular
    @Binds
    internal abstract fun bindPopularTVShowRepository(
        popularTvSeriesPagingRepository: PopularTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @NowPlaying
    @Binds
    internal abstract fun bindAiringTodayTVShowRepository(
        airingTodayTvSeriesPagingRepository: AiringTodayTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Latest
    @Binds
    internal abstract fun bindOnTheAirTVShowRepository(
        onTheAirTvSeriesPagingRepository: OnTheAirTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @TopRated
    @Binds
    internal abstract fun bindTopRatedTVShowRepository(
        topRatedTvSeriesPagingRepository: TopRatedTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Discover
    @Binds
    internal abstract fun bindDiscoverTVShowRepository(
        discoverTvSeriesPagingRepository: DiscoverTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Similar
    @Binds
    internal abstract fun bindSimilarTVShowRepository(
        similarTvSeriesPagingRepository: SimilarTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Search
    @Binds
    internal abstract fun bindSearchTVShowRepository(
        searchTvSeriesPagingRepository: SearchTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindPersonRepository(personRepository: PersonRepository): BaseRepository<Person>

    @Singleton
    @Binds
    internal abstract fun bindBookmarkMovieDetailsRepository(
        bookmarkMovieDetailsRepository: BookmarkMovieDetailsRepositoryImpl,
    ): BookmarkDetailsRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindBookmarkTVShowDetailsRepository(
        bookmarkTVShowDetailsRepository: BookmarkTVShowDetailsRepositoryImpl,
    ): BookmarkDetailsRepository<TVShow>

    @Singleton
    @Binds
    @JvmSuppressWildcards
    internal abstract fun bindBookmarkMovieRepository(
        bookmarkMovieRepository: BookmarkMovieRepository,
    ): BaseRepository<List<Movie>>

    @Singleton
    @Binds
    @JvmSuppressWildcards
    internal abstract fun bindBookmarkTVShowRepository(
        bookmarkTVShowRepository: BookmarkTVShowRepository,
    ): BaseRepository<List<TVShow>>
}
