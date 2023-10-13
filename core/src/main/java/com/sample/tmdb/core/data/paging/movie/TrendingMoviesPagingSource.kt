package com.sample.tmdb.core.data.paging.movie

import android.content.Context
import com.sample.tmdb.core.data.network.MovieService
import com.sample.tmdb.core.data.paging.BasePagingSource
import com.sample.tmdb.core.data.response.asMovieDomainModel
import com.sample.tmdb.core.domain.model.Movie

class TrendingMoviesPagingSource(
    context: Context,
    private val movieApi: MovieService
) : BasePagingSource<Movie>(context) {

    override suspend fun fetchItems(page: Int): List<Movie> =
        movieApi.trendingMovies(page).items.asMovieDomainModel()
}