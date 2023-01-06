package com.android.sample.tmdb.data.paged.movie

import com.android.sample.tmdb.data.network.MovieService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.response.asMovieDomainModel
import com.android.sample.tmdb.domain.model.Movie

class NowPlayingMoviesPagingSource(private val movieApi: MovieService) : BasePagingSource<Movie>() {

    override suspend fun fetchItems(page: Int): List<Movie> =
        movieApi.nowPlayingMovies(page).items.asMovieDomainModel()
}