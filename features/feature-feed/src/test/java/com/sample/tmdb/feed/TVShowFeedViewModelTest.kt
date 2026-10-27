package com.sample.tmdb.feed

import com.sample.tmdb.common.model.MediaType
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.asNavSortType
import com.sample.tmdb.feature.test.TVShowFixtures
import com.sample.tmdb.navigation.TMDbNavKey

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

    override fun expectedNavKey(sortType: SortType): TMDbNavKey = TMDbNavKey.PagingTvShows(sortType.asNavSortType())
}
