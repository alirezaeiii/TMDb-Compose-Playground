package com.sample.tmdb.bookmark

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.domain.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkTVShowViewModel
@Inject
constructor(repository: @JvmSuppressWildcards BaseRepository<List<TVShow>>) :
    BaseViewModel<List<TVShow>>(repository)
