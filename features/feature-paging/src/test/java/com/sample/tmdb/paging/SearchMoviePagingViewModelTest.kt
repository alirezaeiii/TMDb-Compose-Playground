package com.sample.tmdb.paging

import com.sample.tmdb.paging.search.SearchMoviesViewModel

class SearchMoviePagingViewModelTest : BasePagingMovieViewModelTest<SearchMoviesViewModel>() {

    override fun initViewModel() {
        super.viewModel = SearchMoviesViewModel(repository, savedStateHandle)
    }
}
