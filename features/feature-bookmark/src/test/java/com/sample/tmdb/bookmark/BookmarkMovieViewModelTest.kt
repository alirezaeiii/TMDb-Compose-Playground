package com.sample.tmdb.bookmark

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.feature.test.MovieFixtures

class BookmarkMovieViewModelTest :
    BaseBookmarkViewModelTest<Movie>(),
    MovieFixtures {

    override fun initViewModel() {
        viewModel = BookmarkMovieViewModel(repository, languageRepository)
    }
}
