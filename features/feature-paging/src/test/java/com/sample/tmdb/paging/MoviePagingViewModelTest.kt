package com.sample.tmdb.paging

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.main.MoviePagingViewModel
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MoviePagingViewModelTest : BasePagingViewModelTest<Movie, MoviePagingViewModel>() {

    override fun initViewModel() {
        super.viewModel = MoviePagingViewModel(repository, type = SortType.TRENDING)
    }

    @Test
    fun `onSearchClick movie emits Navigate to SearchMovies`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.SearchMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
