package com.sample.tmdb.paging

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.search.SearchTVSeriesViewModel

class SearchTVShowPagingViewModelTest : BasePagingViewModelTest<TVShow, SearchTVSeriesViewModel>() {

    override fun initViewModel() {
        super.viewModel = SearchTVSeriesViewModel(repository, savedStateHandle)
    }

    override val item: TMDbItem
        get() = TVShow(200, "", null, null, null, "", 0.0, 0)

    override val navKey: TMDbNavKey
        get() = TMDbNavKey.TvShowDetail(200)
}
