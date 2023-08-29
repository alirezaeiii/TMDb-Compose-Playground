package com.sample.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TvDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkItemDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkItemDetailsRepository<TVShow>,
    repository: BaseDetailRepository<TvDetails>,
    savedStateHandle: SavedStateHandle
) : BaseDetailViewModel<TvDetails, TVShow>(bookmarkRepository, repository, savedStateHandle)