package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.core.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkMovieViewModel @Inject constructor(
    repository: @JvmSuppressWildcards BaseRepository<List<Movie>>
) : BaseViewModel<List<Movie>>(repository)