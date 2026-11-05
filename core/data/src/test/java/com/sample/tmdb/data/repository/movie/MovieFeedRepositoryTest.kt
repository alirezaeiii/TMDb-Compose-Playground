package com.sample.tmdb.data.repository.movie

import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.MovieResponse
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.source.entity.MovieFeedEntity
import com.sample.tmdb.data.source.local.MovieFeedDao
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFeedRepositoryTest : BaseFeedRepositoryTest<Movie>() {
    @Mock
    private lateinit var api: MovieService

    @Mock
    private lateinit var dao: MovieFeedDao

    override fun initRepository() {
        repository = MovieFeedRepository(context, Dispatchers.Main, api, dao)
    }

    override fun mockApiResponse() = runTest {
        var dbEntities = emptyList<MovieFeedEntity>()
        `when`(dao.getFeed()).thenAnswer { dbEntities }
        doAnswer { invocation ->
            dbEntities = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsert(anyList())

        val movie = MovieResponse(1, "overview", "2024-01-01", null, null, "title", 8.0, 100)
        val wrapper = NetworkTMDbWrapper(listOf(movie))

        `when`(api.trendingMovies()).thenReturn(wrapper)
        `when`(api.popularMovies()).thenReturn(wrapper)
        `when`(api.nowPlayingMovies()).thenReturn(wrapper)
        `when`(api.upcomingMovies()).thenReturn(wrapper)
        `when`(api.topRatedMovies()).thenReturn(wrapper)
        `when`(api.discoverMovies()).thenReturn(wrapper)
        `when`(api.providerMovies(APPLE_TV_PROVIDER_ID)).thenReturn(wrapper)
        `when`(api.providerMovies(NETFLIX_PROVIDER_ID)).thenReturn(wrapper)
        `when`(api.providerMovies(AMAZON_PRIME_PROVIDER_ID)).thenReturn(wrapper)
        `when`(api.providerMovies(GOOGLE_PLAY_PROVIDER_ID)).thenReturn(wrapper)
    }

    override fun mockFailApiResponse() = runTest {
        `when`(dao.getFeed()).thenReturn(emptyList())
        `when`(api.trendingMovies()).thenThrow(RuntimeException())
    }
}
