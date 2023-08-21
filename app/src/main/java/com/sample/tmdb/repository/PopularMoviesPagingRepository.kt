package com.sample.tmdb.repository

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paged.BasePagingSource
import com.sample.tmdb.data.paged.movie.PopularMoviesPagingSource
import com.sample.tmdb.domain.BasePagingRepository
import com.sample.tmdb.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesPagingRepository @Inject constructor(
    private val context: Context,
    private val movieApi: MovieService
) : BasePagingRepository<Movie>() {

    override fun pagingSource(query: String?): BasePagingSource<Movie> =
        PopularMoviesPagingSource(context, movieApi)
}