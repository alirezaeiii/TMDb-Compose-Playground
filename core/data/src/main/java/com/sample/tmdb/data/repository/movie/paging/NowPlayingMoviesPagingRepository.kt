package com.sample.tmdb.data.repository.movie.paging

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.movie.NowPlayingMoviesPagingSource
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.domain.repository.BasePagingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NowPlayingMoviesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieApi: MovieService,
) : BasePagingRepository<Movie>() {
    override fun pagingSource(query: String?, id: Int?): BasePagingSource<Movie> =
        NowPlayingMoviesPagingSource(context, movieApi)
}
