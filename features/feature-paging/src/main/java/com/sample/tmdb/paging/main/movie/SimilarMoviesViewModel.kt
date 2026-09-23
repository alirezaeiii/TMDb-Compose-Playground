package com.sample.tmdb.paging.main.movie

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.domain.utils.Similar
import com.sample.tmdb.paging.main.BaseMoviePagingViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = SimilarMoviesViewModel.Factory::class)
class SimilarMoviesViewModel @AssistedInject constructor(
    @Similar repository: BasePagingRepository<Movie>,
    @Assisted similarId: Int,
) : BaseMoviePagingViewModel(repository, similarId) {

    @AssistedFactory
    interface Factory {
        fun create(similarId: Int): SimilarMoviesViewModel
    }
}
