package com.sample.tmdb.paging.main.tvshow

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.paging.main.BaseTvShowPagingViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = SimilarTvSeriesViewModel.Factory::class)
class SimilarTvSeriesViewModel @AssistedInject constructor(
    @Similar repository: BasePagingRepository<TVShow>,
    @Assisted similarId: Int,
) : BaseTvShowPagingViewModel(repository, similarId) {

    @AssistedFactory
    interface Factory {
        fun create(similarId: Int): SimilarTvSeriesViewModel
    }
}
