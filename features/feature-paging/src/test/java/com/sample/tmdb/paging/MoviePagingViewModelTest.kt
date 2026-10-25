package com.sample.tmdb.paging

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.feature.test.MovieFixtures
import com.sample.tmdb.navigation.model.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.main.MoviePagingViewModel

class MoviePagingViewModelTest :
    BaseMainPagingViewModelTest<Movie>(),
    MovieFixtures {

    override fun initViewModel(sortType: SortType) {
        super.viewModel = MoviePagingViewModel(repository, type = sortType)
    }

    override val searchNavKey: TMDbNavKey
        get() = TMDbNavKey.SearchMovies
}
