package com.sample.tmdb.core.data.repository

import android.content.Context
import com.sample.tmdb.core.data.network.MovieService
import com.sample.tmdb.core.data.paging.BasePagingSource
import com.sample.tmdb.core.data.paging.movie.PopularMoviesPagingSource
import com.sample.tmdb.core.domain.repository.BasePagingRepository
import com.sample.tmdb.core.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieApi: MovieService
) : BasePagingRepository<Movie>() {

    override fun pagingSource(query: String?): BasePagingSource<Movie> =
        PopularMoviesPagingSource(context, movieApi)
}