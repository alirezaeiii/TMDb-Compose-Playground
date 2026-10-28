package com.sample.tmdb.data.repository.movie

import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
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
        `when`(api.trendingMovies()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.popularMovies()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.nowPlayingMovies()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.upcomingMovies()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.topRatedMovies()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.discoverMovies()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.providerMovies(APPLE_TV_PROVIDER_ID)).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.providerMovies(NETFLIX_PROVIDER_ID)).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.providerMovies(AMAZON_PRIME_PROVIDER_ID)).thenReturn(NetworkTMDbWrapper(emptyList()))
    }

    override fun mockFailApiResponse() = runTest {
        `when`(api.trendingMovies()).thenThrow(RuntimeException())
    }
}
