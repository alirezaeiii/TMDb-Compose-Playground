package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.feature.test.MovieFixtures
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey

class MovieDetailViewModelTest :
    BaseDetailViewModelTest<MovieDetails, Movie>(),
    MovieFixtures {
    override fun initViewModel() {
        super.viewModel = MovieDetailViewModel(bookmarkRepository, repository, TMDB_ITEM_ID)
    }

    override val similarNavKey: TMDbNavKey
        get() = TMDbNavKey.PagingMovies(SortType.SIMILAR, TMDB_ITEM_ID)
}
