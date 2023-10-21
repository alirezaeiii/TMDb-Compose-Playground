package com.sample.tmdb.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkItemDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkItemDetailsRepository<Movie>,
    repository: BaseDetailRepository<MovieDetails>,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<MovieDetails, Movie>(bookmarkRepository, repository, savedStateHandle)