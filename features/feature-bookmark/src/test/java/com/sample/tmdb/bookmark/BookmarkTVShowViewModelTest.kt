package com.sample.tmdb.bookmark

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.feature.test.TVShowFixtures

class BookmarkTVShowViewModelTest :
    BaseBookmarkViewModelTest<TVShow>(),
    TVShowFixtures {

    override fun initViewModel() {
        viewModel = BookmarkTVShowViewModel(repository, languageRepository)
    }
}
