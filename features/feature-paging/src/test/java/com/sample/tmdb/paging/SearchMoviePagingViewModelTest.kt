package com.sample.tmdb.paging

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.paging.search.SearchMoviesViewModel

class SearchMoviePagingViewModelTest :
    BaseSearchPagingViewModelTest<Movie>(),
    MovieFixtures {

    override fun initViewModel() {
        super.viewModel = SearchMoviesViewModel(repository, savedStateHandle)
    }
}
