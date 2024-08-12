package com.sample.tmdb.feed

import com.sample.tmdb.domain.model.Movie

class MovieFeedViewModelTest: BaseFeedViewModelTest<Movie>() {

    override fun initViewModel() {
        super.viewModel = MovieFeedViewModel(repository)
    }
}