package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TVShowDetails
import com.sample.tmdb.feature.test.TVShowFixtures
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey

class TVShowDetailViewModelTest :
    BaseDetailViewModelTest<TVShowDetails, TVShow>(),
    TVShowFixtures {
    override fun initViewModel() {
        super.viewModel = TVShowDetailViewModel(bookmarkRepository, repository, TMDB_ITEM_ID)
    }

    override val similarNavKey: TMDbNavKey
        get() = TMDbNavKey.PagingTvShows(SortType.SIMILAR, TMDB_ITEM_ID)
}
