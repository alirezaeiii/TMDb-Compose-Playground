package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails

class MovieDetailViewModelTest: BaseDetailViewModelTest<MovieDetails, Movie>() {

    override fun initViewModel() {
        super.viewModel = MovieDetailViewModel(bookmarkRepository, repository, savedStateHandle)
    }
}