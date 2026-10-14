package com.sample.tmdb.paging

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.feature.test.TVShowFixtures
import com.sample.tmdb.paging.search.SearchTVSeriesViewModel

class SearchTVShowPagingViewModelTest :
    BaseSearchPagingViewModelTest<TVShow>(),
    TVShowFixtures {

    override fun initViewModel() {
        super.viewModel = SearchTVSeriesViewModel(repository, savedStateHandle)
    }
}
