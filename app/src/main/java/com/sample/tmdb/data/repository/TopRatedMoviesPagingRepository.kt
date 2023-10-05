package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.BasePagingSource
import com.sample.tmdb.data.paging.movie.TopRatedMoviesPagingSource
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedMoviesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieApi: MovieService
) : BasePagingRepository<Movie>() {

    override fun pagingSource(query: String?): BasePagingSource<Movie> =
        TopRatedMoviesPagingSource(context, movieApi)
}