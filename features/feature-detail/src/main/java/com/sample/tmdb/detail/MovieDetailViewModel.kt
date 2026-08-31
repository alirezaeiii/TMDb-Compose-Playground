package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = MovieDetailViewModel.Factory::class)
class MovieDetailViewModel @AssistedInject constructor(
    bookmarkRepository: BookmarkDetailsRepository<Movie>,
    repository: BaseDetailRepository<MovieDetails>,
    @Assisted tmdbId: Int,
) : BaseDetailViewModel<MovieDetails, Movie>(bookmarkRepository, repository, tmdbId) {

    @AssistedFactory
    interface Factory {
        fun create(tmdbId: Int): MovieDetailViewModel
    }
}
