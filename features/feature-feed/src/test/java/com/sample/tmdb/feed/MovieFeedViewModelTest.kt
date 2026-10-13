package com.sample.tmdb.feed

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey

class MovieFeedViewModelTest : BaseFeedViewModelTest<Movie>() {
    override fun initViewModel() {
        super.viewModel = MovieFeedViewModel(repository, languageRepository)
    }

    override val searchNavKey: TMDbNavKey
        get() = TMDbNavKey.SearchMovies

    override val item: TMDbItem
        get() = Movie(10, "", null, null, null, "", 0.0, 0)

    override val detailNavKey: TMDbNavKey
        get() = TMDbNavKey.MovieDetail(10)

    override val contentType: ContentType
        get() = ContentType.MOVIE

    override val onMoreNavKey: TMDbNavKey
        get() = TMDbNavKey.PagingMovies(SortType.TRENDING)
}
