package com.sample.tmdb.data.paging.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.BaseRemoteMediatorTest
import com.sample.tmdb.data.response.MovieResponse
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.source.entity.MoviePagingEntity
import com.sample.tmdb.data.source.local.MoviePagingDao
import com.sample.tmdb.data.source.local.MovieRemoteKeyDao
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.SortType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediatorTest : BaseRemoteMediatorTest() {

    private val api: MovieService = mockk()
    private val moviePagingDao: MoviePagingDao = mockk(relaxed = true)
    private val movieRemoteKeyDao: MovieRemoteKeyDao = mockk(relaxed = true)

    private val movie1 = MovieResponse(
        1,
        "overview1",
        null,
        null,
        null,
        "name1",
        1.1,
        1,
    )
    private val movie2 = MovieResponse(
        2,
        "overview2",
        null,
        null,
        null,
        "name2",
        1.1,
        1,
    )

    @Before
    fun setup() {
        every { database.moviePagingDao } returns moviePagingDao
        every { database.movieRemoteKeyDao } returns movieRemoteKeyDao
    }

    private fun mockApiResponse(sortType: SortType) {
        coEvery { getApiCall(sortType) } returns NetworkTMDbWrapper(listOf(movie1, movie2))
    }

    private suspend fun getApiCall(sortType: SortType): NetworkTMDbWrapper<MovieResponse> = when (sortType) {
        SortType.TRENDING -> api.trendingMovies(1)
        SortType.DISCOVER -> api.discoverMovies(1)
        SortType.MOST_POPULAR -> api.popularMovies(1)
        SortType.NOW_PLAYING -> api.nowPlayingMovies(1)
        SortType.UPCOMING -> api.upcomingMovies(1)
        SortType.HIGHEST_RATED -> api.topRatedMovies(1)
        SortType.SIMILAR -> api.fetchSimilarMovies(1, 1)
        SortType.APPLE_TV -> api.providerMovies(APPLE_TV_PROVIDER_ID, 1)
        SortType.NETFLIX -> api.providerMovies(NETFLIX_PROVIDER_ID, 1)
        SortType.AMAZON_PRIME -> api.providerMovies(AMAZON_PRIME_PROVIDER_ID, 1)
        SortType.GOOGLE_PLAY -> api.providerMovies(GOOGLE_PLAY_PROVIDER_ID, 1)
    }

    @Test
    fun refreshMovieRemoteMediatorSuccess() = runTest {
        SortType.entries.forEach { sortType ->
            mockApiResponse(sortType)
            val mediator = MovieRemoteMediator(
                database = database,
                movieApi = api,
                sortType = sortType,
                movieId = if (sortType == SortType.SIMILAR) 1 else null,
                context = context,
            )
            val pagingState = PagingState<Int, MoviePagingEntity>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(20),
                leadingPlaceholderCount = 0,
            )
            val result = mediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse(result.endOfPaginationReached)
        }
    }
}
