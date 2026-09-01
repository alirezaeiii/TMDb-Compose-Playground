package com.sample.tmdb.detail

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TVShowDetails

class TVShowDetailViewModelTest : BaseDetailViewModelTest<TVShowDetails, TVShow>() {
    override fun initViewModel() {
        super.viewModel = TVShowDetailViewModel(bookmarkRepository, repository, TMDB_ITEM_ID)
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
