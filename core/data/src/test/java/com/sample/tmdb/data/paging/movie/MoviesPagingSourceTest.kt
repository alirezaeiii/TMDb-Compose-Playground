package com.sample.tmdb.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.BasePagingSourceTest
import com.sample.tmdb.data.response.MovieResponse
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.domain.model.SortType
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesPagingSourceTest : BasePagingSourceTest<Movie, MovieResponse>() {

    @Mock
    private lateinit var api: MovieService

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

    override fun mockApiResponse(sortType: SortType) = runTest {
        `when`(getApiCall(sortType)).thenReturn(NetworkTMDbWrapper(listOf(movie1, movie2)))
    }

    override suspend fun getApiCall(sortType: SortType): NetworkTMDbWrapper<MovieResponse> = when (sortType) {
        SortType.TRENDING -> api.trendingMovies(1)
        SortType.DISCOVER -> api.discoverMovies(1)
        SortType.MOST_POPULAR -> api.popularMovies(1)
        SortType.NOW_PLAYING -> api.nowPlayingMovies(1)
        SortType.UPCOMING -> api.upcomingMovies(1)
        SortType.HIGHEST_RATED -> api.topRatedMovies(1)
        SortType.SIMILAR -> api.fetchSimilarMovies(1, 1)
    }

    override fun getPagingDataSource(sortType: SortType): BasePagingSource<Movie> = MoviePagingSource(
        context,
        api,
        sortType,
        movieId = if (sortType == SortType.SIMILAR) 1 else null,
    )

    @Test
    fun pageKeyedShowPagingSource() = runTest {
        SortType.entries.forEach { sortType ->
            mockApiResponse(sortType)
            assertEquals(
                expected = Page(
                    data = listOf(movie1, movie2).asMovieDomainModel(),
                    prevKey = null,
                    nextKey = 2,
                ),
                actual = getPagingDataSource(sortType).load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false,
                    ),
                ),
            )
        }
    }
}
