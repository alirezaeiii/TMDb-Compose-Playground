package com.sample.tmdb.data.repository.movie

import app.cash.turbine.test
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.domain.model.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
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
    }

    @Test
    fun `load feeds failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            `when`(api.trendingMovies()).thenThrow(RuntimeException())
            repository.getResult().test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
