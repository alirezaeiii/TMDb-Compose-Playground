package com.sample.tmdb.data.paging.movie

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.paging.BasePagingSource

class SearchMoviesPagingSource(
    context: Context,
    private val movieApi: MovieService,
    private val query: String
) : BasePagingSource<Movie>(context) {

    override suspend fun fetchItems(page: Int): List<Movie> =
        movieApi.searchMovies(page, query).items.asMovieDomainModel()
}