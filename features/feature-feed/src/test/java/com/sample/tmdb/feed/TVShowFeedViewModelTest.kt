package com.sample.tmdb.feed

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey

class TVShowFeedViewModelTest : BaseFeedViewModelTest<TVShow>() {
    override fun initViewModel() {
        super.viewModel = TVShowFeedViewModel(repository, languageRepository)
    }

    override val searchNavKey: TMDbNavKey
        get() = TMDbNavKey.SearchTvShows

    override val item: TMDbItem
        get() = TVShow(10, "", null, null, null, "", 0.0, 0)

    override val detailNavKey: TMDbNavKey
        get() = TMDbNavKey.TvShowDetail(10)

    override val contentType: ContentType
        get() = ContentType.TV_SHOW

    override val onMoreNavKey: TMDbNavKey
        get() = TMDbNavKey.PagingTvShows(SortType.TRENDING)
}
