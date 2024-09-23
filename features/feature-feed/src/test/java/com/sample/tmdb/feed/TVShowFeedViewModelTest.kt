package com.sample.tmdb.feed

import com.sample.tmdb.domain.model.TVShow

class TVShowFeedViewModelTest : BaseFeedViewModelTest<TVShow>() {
    override fun initViewModel() {
        super.viewModel = TVShowFeedViewModel(repository)
    }
}
