package com.sample.tmdb.feed

import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.feature.test.MovieFixtures
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.asNavSortType

class MovieFeedViewModelTest :
    BaseFeedViewModelTest<Movie>(),
    MovieFixtures {
    override fun initViewModel() {
        super.viewModel = MovieFeedViewModel(repository, languageRepository)
    }

    override val searchNavKey: TMDbNavKey
        get() = TMDbNavKey.SearchMovies

    override val mediaType: MediaType
        get() = MediaType.MOVIE

    override fun expectedNavKey(sortType: SortType): TMDbNavKey = TMDbNavKey.PagingMovies(sortType.asNavSortType())
}
