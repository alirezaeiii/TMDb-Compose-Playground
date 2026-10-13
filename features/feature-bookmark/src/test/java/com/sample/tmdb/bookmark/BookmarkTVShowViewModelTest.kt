package com.sample.tmdb.bookmark

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.TMDbNavKey

class BookmarkTVShowViewModelTest : BaseBookmarkViewModelTest<TVShow>() {

    override fun initViewModel() {
        viewModel = BookmarkTVShowViewModel(repository, languageRepository)
    }

    override val item: TMDbItem
        get() = TVShow(25, "", null, null, null, "", 0.0, 0)

    override val navKey: TMDbNavKey
        get() = TMDbNavKey.TvShowDetail(25)
}
