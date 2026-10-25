package com.sample.tmdb.feed

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.feature.test.TVShowFixtures
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.MediaType
import com.sample.tmdb.navigation.model.SortType

class TVShowFeedViewModelTest :
    BaseFeedViewModelTest<TVShow>(),
    TVShowFixtures {
    override fun initViewModel() {
        super.viewModel = TVShowFeedViewModel(repository, languageRepository)
    }

    override val searchNavKey: TMDbNavKey
        get() = TMDbNavKey.SearchTvShows

    override val mediaType: MediaType
        get() = MediaType.TV_SHOW

    override fun expectedNavKey(sortType: SortType): TMDbNavKey = TMDbNavKey.PagingTvShows(sortType)
}
