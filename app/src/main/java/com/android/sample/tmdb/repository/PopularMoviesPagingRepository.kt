package com.android.sample.tmdb.repository

import com.android.sample.tmdb.data.network.MovieService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.paged.movie.PopularMoviesPagingSource
import com.android.sample.tmdb.domain.BasePagingRepository
import com.android.sample.tmdb.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesPagingRepository @Inject constructor(private val movieApi: MovieService) :
    BasePagingRepository<Movie>() {

    override val pagingSource: BasePagingSource<Movie>
        get() = PopularMoviesPagingSource(movieApi)
}