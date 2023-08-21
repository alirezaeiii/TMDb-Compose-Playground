package com.sample.tmdb.data.paged.movie

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paged.BasePagingSource
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.domain.model.Movie

class SearchMoviesPagingSource(
    context: Context,
    private val movieApi: MovieService,
    private val query: String
) : BasePagingSource<Movie>(context) {

    override suspend fun fetchItems(page: Int): List<Movie> =
        movieApi.searchMovies(page, query).items.asMovieDomainModel()
}