package com.sample.tmdb.data.repository.movie

import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.TMDbWrapper
import com.sample.tmdb.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFeedRepositoryTest : BaseFeedRepositoryTest<Movie>() {

    @Mock
    private lateinit var api: MovieService

    override fun initRepository() {
        repository = MovieFeedRepository(context, Dispatchers.Main, api)
    }

    override fun mockApiResponse() = runTest {
        `when`(api.trendingMovies()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.popularMovies()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.nowPlayingMovies()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.upcomingMovies()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.topRatedMovies()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.discoverMovies()).thenReturn(TMDbWrapper(emptyList()))
    }


}