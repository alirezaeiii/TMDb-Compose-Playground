package com.sample.tmdb.paging

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

abstract class BasePagingViewModelTest<T : TMDbItem, VM : BasePagingViewModel<T>> {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val repository = mockk<BasePagingRepository<T>>()

    protected lateinit var viewModel: VM

    protected abstract fun initViewModel()

    @Test
    fun `onItemClick movie emits Navigate to MovieDetail`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        val movie = Movie(100, "", null, null, null, "", 0.0, 0)

        viewModel.uiEvent.test {
            viewModel.onItemClick(movie)
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.MovieDetail(100)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onItemClick tvShow emits Navigate to TvShowDetail`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        val tvShow = TVShow(200, "", null, null, null, "", 0.0, 0)

        viewModel.uiEvent.test {
            viewModel.onItemClick(tvShow)
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.TvShowDetail(200)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(PagingUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
