package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TVShowDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = TVShowDetailViewModel.Factory::class)
class TVShowDetailViewModel @AssistedInject constructor(
    bookmarkRepository: BookmarkDetailsRepository<TVShow>,
    repository: BaseDetailRepository<TVShowDetails>,
    @Assisted tmdbId: Int,
) : BaseDetailViewModel<TVShowDetails, TVShow>(bookmarkRepository, repository, tmdbId) {

    @AssistedFactory
    interface Factory {
        fun create(tmdbId: Int): TVShowDetailViewModel
    }
}
