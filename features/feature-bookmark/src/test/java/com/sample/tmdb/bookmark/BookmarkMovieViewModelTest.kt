package com.sample.tmdb.bookmark

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.navigation.TMDbNavKey

class BookmarkMovieViewModelTest : BaseBookmarkViewModelTest<Movie>() {

    override fun initViewModel() {
        viewModel = BookmarkMovieViewModel(repository, languageRepository)
    }

    override val item: TMDbItem
        get() = Movie(15, "", null, null, null, "", 0.0, 0)

    override val navKey: TMDbNavKey
        get() = TMDbNavKey.MovieDetail(15)
}
