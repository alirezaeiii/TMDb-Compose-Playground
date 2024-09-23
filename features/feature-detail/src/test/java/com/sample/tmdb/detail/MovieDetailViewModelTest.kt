package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails

class MovieDetailViewModelTest : BaseDetailViewModelTest<MovieDetails, Movie>() {
    override fun initViewModel() {
        super.viewModel = MovieDetailViewModel(bookmarkRepository, repository, savedStateHandle)
    }

    override val tmdbItem: Movie
        get() =
            Movie(
                TMDB_ITEM_ID,
                "overview",
                null,
                null,
                null,
                "name",
                1.0,
                1,
            )
}
