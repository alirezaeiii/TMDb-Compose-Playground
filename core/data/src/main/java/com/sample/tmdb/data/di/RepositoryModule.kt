package com.sample.tmdb.data.di

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.data.repository.PersonRepository
import com.sample.tmdb.data.repository.movie.MovieFeedRepository
import com.sample.tmdb.data.repository.movie.detail.BookmarkMovieDetailsRepositoryImpl
import com.sample.tmdb.data.repository.movie.detail.BookmarkMovieRepository
import com.sample.tmdb.data.repository.movie.detail.MovieDetailRepository
import com.sample.tmdb.data.repository.movie.paging.MoviesPagingRepository
import com.sample.tmdb.data.repository.movie.paging.SearchMoviesPagingRepository
import com.sample.tmdb.data.repository.tvshow.TVShowFeedRepository
import com.sample.tmdb.data.repository.tvshow.detail.BookmarkTVShowDetailsRepositoryImpl
import com.sample.tmdb.data.repository.tvshow.detail.BookmarkTVShowRepository
import com.sample.tmdb.data.repository.tvshow.detail.TVShowDetailRepository
import com.sample.tmdb.data.repository.tvshow.paging.SearchTVSeriesPagingRepository
import com.sample.tmdb.data.repository.tvshow.paging.TVSeriesPagingRepository
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TVShowDetails
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import com.sample.tmdb.domain.utils.Search
import com.sample.tmdb.domain.utils.TMDb
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
    ): BaseDetailRepository<TVShowDetails>

    @Singleton
    @TMDb
    @Binds
    internal abstract fun bindMoviePagingRepository(
        moviesPagingRepository: MoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @Search
    @Binds
    internal abstract fun bindSearchMoviesRepository(
        searchMoviesPagingRepository: SearchMoviesPagingRepository,
    ): BasePagingRepository<Movie>

    @Singleton
    @TMDb
    @Binds
    internal abstract fun bindTVShowPagingRepository(
        tVSeriesPagingRepository: TVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Search
    @Binds
    internal abstract fun bindSearchTVShowRepository(
        searchTvSeriesPagingRepository: SearchTVSeriesPagingRepository,
    ): BasePagingRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindPersonRepository(personRepository: PersonRepository): BaseRepository<Person, Int>

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
    internal abstract fun bindBookmarkMovieRepository(
        bookmarkMovieRepository: BookmarkMovieRepository,
    ): BaseBookmarkRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindBookmarkTVShowRepository(
        bookmarkTVShowRepository: BookmarkTVShowRepository,
    ): BaseBookmarkRepository<TVShow>
}
