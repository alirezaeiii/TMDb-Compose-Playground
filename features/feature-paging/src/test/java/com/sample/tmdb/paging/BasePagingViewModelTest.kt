package com.sample.tmdb.paging

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.ui.SearchMovies
import com.sample.tmdb.common.ui.SearchTvShows
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.paging.main.BaseMoviePagingViewModel
import com.sample.tmdb.paging.main.BaseTvShowPagingViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BasePagingViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val movieRepository = mockk<BasePagingRepository<Movie>>()
    private val tvShowRepository = mockk<BasePagingRepository<TVShow>>()

    @Test
    fun `onItemClick movie emits Navigate to MovieDetail`() = runTest {
        every { movieRepository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = object : BaseMoviePagingViewModel(movieRepository) {}

        val movie = mockk<Movie>(relaxed = true)
        every { movie.id } returns 100

        viewModel.uiEvent.test {
            viewModel.onItemClick(movie)
            assertEquals(PagingUiEvent.Navigate(MovieDetail(100)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onItemClick tvShow emits Navigate to TvShowDetail`() = runTest {
        every { tvShowRepository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = object : BaseTvShowPagingViewModel(tvShowRepository) {}

        val tvShow = mockk<TVShow>(relaxed = true)
        every { tvShow.id } returns 200

        viewModel.uiEvent.test {
            viewModel.onItemClick(tvShow)
            assertEquals(PagingUiEvent.Navigate(TvShowDetail(200)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchClick movie emits Navigate to SearchMovies`() = runTest {
        every { movieRepository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = object : BaseMoviePagingViewModel(movieRepository) {}

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(PagingUiEvent.Navigate(SearchMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchClick tvShow emits Navigate to SearchTvShows`() = runTest {
        every { tvShowRepository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = object : BaseTvShowPagingViewModel(tvShowRepository) {}

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(PagingUiEvent.Navigate(SearchTvShows), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { movieRepository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = object : BaseMoviePagingViewModel(movieRepository) {}

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(PagingUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
