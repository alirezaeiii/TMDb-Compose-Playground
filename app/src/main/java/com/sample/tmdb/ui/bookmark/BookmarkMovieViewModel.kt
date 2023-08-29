package com.sample.tmdb.ui.bookmark

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkMovieViewModel @Inject constructor(
    repository: @JvmSuppressWildcards BaseRepository<List<Movie>>
) : BaseViewModel<List<Movie>>(repository)