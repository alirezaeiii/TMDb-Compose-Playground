package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TvDetails

class TVShowDetailViewModelTest : BaseDetailViewModelTest<TvDetails, TVShow>() {
    override fun initViewModel() {
        super.viewModel = TVShowDetailViewModel(bookmarkRepository, repository, savedStateHandle)
    }

    override val tmdbItem: TVShow
        get() =
            TVShow(
                TMDB_ITEM_ID,
                "overview",
                null,
                null,
                null,
                "name",
                1.0,
                1,
            )
}
