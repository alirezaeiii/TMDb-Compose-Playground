package com.android.sample.tmdb.di

import com.android.sample.tmdb.domain.BaseDetailRepository
import com.android.sample.tmdb.domain.BaseFeedRepository
import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.domain.model.MovieDetails
import com.android.sample.tmdb.domain.model.TVShow
import com.android.sample.tmdb.domain.model.TvDetails
import com.android.sample.tmdb.repository.MovieDetailRepository
import com.android.sample.tmdb.repository.MovieFeedRepository
import com.android.sample.tmdb.repository.TVShowDetailRepository
import com.android.sample.tmdb.repository.TVShowFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
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
}