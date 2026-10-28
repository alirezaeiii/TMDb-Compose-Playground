package com.sample.tmdb.data.paging.movie

import android.content.Context
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.paging.BasePagingSource

class MoviePagingSource(
    context: Context,
    private val movieApi: MovieService,
    private val sortType: SortType,
    private val movieId: Int? = null,
) : BasePagingSource<Movie>(context) {
    override suspend fun fetchItems(page: Int): List<Movie> = when (sortType) {
        SortType.TRENDING -> movieApi.trendingMovies(page).items.asMovieDomainModel()
        SortType.DISCOVER -> movieApi.discoverMovies(page).items.asMovieDomainModel()
        SortType.MOST_POPULAR -> movieApi.popularMovies(page).items.asMovieDomainModel()
        SortType.NOW_PLAYING -> movieApi.nowPlayingMovies(page).items.asMovieDomainModel()
        SortType.UPCOMING -> movieApi.upcomingMovies(page).items.asMovieDomainModel()
        SortType.HIGHEST_RATED -> movieApi.topRatedMovies(page).items.asMovieDomainModel()
        SortType.SIMILAR -> movieApi.fetchSimilarMovies(movieId!!, page).items.asMovieDomainModel()
        SortType.APPLE_TV -> movieApi.providerMovies(APPLE_TV_PROVIDER_ID, page).items.asMovieDomainModel()
        SortType.NETFLIX -> movieApi.providerMovies(NETFLIX_PROVIDER_ID, page).items.asMovieDomainModel()
        SortType.AMAZON_PRIME -> movieApi.providerMovies(AMAZON_PRIME_PROVIDER_ID, page).items.asMovieDomainModel()
    }
}
