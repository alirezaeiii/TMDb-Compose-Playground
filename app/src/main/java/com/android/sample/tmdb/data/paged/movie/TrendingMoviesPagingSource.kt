package com.android.sample.tmdb.data.paged.movie

import android.content.Context
import com.android.sample.tmdb.data.network.MovieService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.response.asMovieDomainModel
import com.android.sample.tmdb.domain.model.Movie

class TrendingMoviesPagingSource(
    context: Context,
    private val movieApi: MovieService
) : BasePagingSource<Movie>(context) {

    override suspend fun fetchItems(page: Int): List<Movie> =
        movieApi.trendingMovies(page).items.asMovieDomainModel()
}