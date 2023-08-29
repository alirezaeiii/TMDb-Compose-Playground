package com.sample.tmdb.ui.bookmark

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkTVShowViewModel @Inject constructor(
    repository: @JvmSuppressWildcards BaseRepository<List<TVShow>>
) : BaseViewModel<List<TVShow>>(repository)