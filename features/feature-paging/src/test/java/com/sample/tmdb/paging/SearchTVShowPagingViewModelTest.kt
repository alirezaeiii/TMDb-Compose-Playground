package com.sample.tmdb.paging

import com.sample.tmdb.paging.search.SearchTVSeriesViewModel

class SearchTVShowPagingViewModelTest : BasePagingTVShowViewModelTest<SearchTVSeriesViewModel>() {

    override fun initViewModel() {
        super.viewModel = SearchTVSeriesViewModel(repository, savedStateHandle)
    }
}
