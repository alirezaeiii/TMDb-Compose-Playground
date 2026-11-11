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
import com.sample.tmdb.data.source.entity.SearchMoviePagingEntity
import com.sample.tmdb.data.source.local.SearchMoviePagingDao
import com.sample.tmdb.data.source.local.SearchMovieRemoteKeyDao
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class SearchMovieRemoteMediatorTest : BaseRemoteMediatorTest() {

    private val api: MovieService = mockk()
    private val searchMoviesPagingDao: SearchMoviePagingDao = mockk(relaxed = true)
    private val searchMovieRemoteKeyDao: SearchMovieRemoteKeyDao = mockk(relaxed = true)

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
        every { database.searchMoviesPagingDao } returns searchMoviesPagingDao
        every { database.searchMovieRemoteKeyDao } returns searchMovieRemoteKeyDao
    }

    @Test
    fun refreshSearchMovieRemoteMediatorSuccess() = runTest {
        val query = "query"
        coEvery { api.searchMovies(1, query) } returns NetworkTMDbWrapper(listOf(movie1, movie2))
        val mediator = SearchMovieRemoteMediator(
            database = database,
            movieApi = api,
            query = query,
            context = context,
        )
        val pagingState = PagingState<Int, SearchMoviePagingEntity>(
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
