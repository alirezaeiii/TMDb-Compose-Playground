package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TvDetails

class TVShowDetailViewModelTest: BaseDetailViewModelTest<TvDetails, TVShow>() {

    override fun initViewModel() {
        super.viewModel = TVShowDetailViewModel(bookmarkRepository, repository, savedStateHandle)
    }
}