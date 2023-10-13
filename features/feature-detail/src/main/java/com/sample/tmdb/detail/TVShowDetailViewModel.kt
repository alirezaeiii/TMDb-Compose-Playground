package com.sample.tmdb.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.core.domain.model.TVShow
import com.sample.tmdb.core.domain.model.TvDetails
import com.sample.tmdb.core.domain.repository.BaseDetailRepository
import com.sample.tmdb.core.domain.repository.BookmarkItemDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkItemDetailsRepository<TVShow>,
    repository: BaseDetailRepository<TvDetails>,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<TvDetails, TVShow>(bookmarkRepository, repository, savedStateHandle)