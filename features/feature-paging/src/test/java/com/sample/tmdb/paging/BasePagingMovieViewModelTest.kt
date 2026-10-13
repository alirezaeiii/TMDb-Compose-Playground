package com.sample.tmdb.paging

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.navigation.TMDbNavKey

abstract class BasePagingMovieViewModelTest<VM: BasePagingViewModel<Movie>> : BasePagingViewModelTest<Movie, VM>() {

    override val item: TMDbItem
        get() = Movie(100, "", null, null, null, "", 0.0, 0)

    override val navKey: TMDbNavKey
        get() = TMDbNavKey.MovieDetail(100)
}