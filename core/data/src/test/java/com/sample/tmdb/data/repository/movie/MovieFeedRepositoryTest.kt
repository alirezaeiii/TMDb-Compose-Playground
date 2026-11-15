package com.sample.tmdb.data.repository.movie

import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.GenresDTO
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
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieFeedRepositoryTest : BaseFeedRepositoryTest<Movie>() {
    @Mock
    private lateinit var api: MovieService

    @Mock
    private lateinit var dao: MovieFeedDao

    override fun initRepository() {
        repository = MovieFeedRepository(context, Dispatchers.Main, api, dao)
    }

    override fun mockApiResponse() = runTest {
        var dbGenres = emptyList<com.sample.tmdb.data.source.entity.MovieGenreEntity>()
        `when`(dao.getGenres()).thenAnswer { dbGenres }
        doAnswer { invocation ->
            dbGenres = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsertGenres(any())

        var dbEntities = emptyList<MovieFeedEntity>()
        `when`(dao.getFeed(anyString())).thenAnswer { dbEntities }
        doAnswer { invocation ->
            dbEntities = invocation.getArgument(1)
            null
        }.`when`(dao).deleteAndInsert(anyString(), any())

        `when`(api.fetchGenres()).thenReturn(GenresDTO(listOf(com.sample.tmdb.data.response.GenreDTO(1, "Action"))))

        val movie = MovieResponse(1, "overview", "2024-01-01", null, null, "title", 8.0, 100)
        val wrapper = NetworkTMDbWrapper(listOf(movie))

        `when`(api.trendingMovies(anyInt())).thenReturn(wrapper)
        `when`(api.popularMovies(anyInt())).thenReturn(wrapper)
        `when`(api.nowPlayingMovies(anyInt())).thenReturn(wrapper)
        `when`(api.upcomingMovies(anyInt())).thenReturn(wrapper)
        `when`(api.topRatedMovies(anyInt())).thenReturn(wrapper)
        `when`(api.discoverMovies(anyInt(), anyOrNull())).thenReturn(wrapper)
        `when`(api.providerMovies(eq(APPLE_TV_PROVIDER_ID), anyInt(), anyOrNull(), anyString())).thenReturn(wrapper)
        `when`(api.providerMovies(eq(NETFLIX_PROVIDER_ID), anyInt(), anyOrNull(), anyString())).thenReturn(wrapper)
        `when`(api.providerMovies(eq(AMAZON_PRIME_PROVIDER_ID), anyInt(), anyOrNull(), anyString())).thenReturn(wrapper)
        `when`(api.providerMovies(eq(GOOGLE_PLAY_PROVIDER_ID), anyInt(), anyOrNull(), anyString())).thenReturn(wrapper)
    }

    override fun mockFailApiResponse() = runTest {
        `when`(dao.getFeed(anyString())).thenReturn(emptyList())
        `when`(dao.getGenres()).thenReturn(emptyList())
        `when`(api.trendingMovies(anyInt())).thenThrow(RuntimeException())
    }
}
