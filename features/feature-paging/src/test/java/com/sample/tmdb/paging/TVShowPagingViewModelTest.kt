package com.sample.tmdb.paging

import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.feature.test.TVShowFixtures
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.main.TVShowPagingViewModel

class TVShowPagingViewModelTest :
    BaseMainPagingViewModelTest<TVShow>(),
    TVShowFixtures {

    override fun initViewModel(sortType: SortType) {
        super.viewModel = TVShowPagingViewModel(repository, type = sortType)
    }

    override val searchNavKey: TMDbNavKey
        get() = TMDbNavKey.SearchTvShows
}
